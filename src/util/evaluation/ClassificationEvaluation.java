/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.evaluation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author vietan
 */
public class ClassificationEvaluation {

    private int[] trueClasses;
    private int[] predClasses;
    private ArrayList<Measurement> measurements;

    public ClassificationEvaluation(int[] trueClasses, int[] predClasses) {
        this.trueClasses = trueClasses;
        this.predClasses = predClasses;
        this.measurements = new ArrayList<Measurement>();
    }

    public ArrayList<Measurement> getMeasurements() {
        return this.measurements;
    }

    public void computePRF1() {
        Set<Integer> classes = new HashSet<Integer>();
        for (int i = 0; i < trueClasses.length; i++) {
            classes.add(trueClasses[i]);
        }

        for (int c : classes) {
            int truePos = 0;
            int falsePos = 0;
            int falseNeg = 0;
            int trueNeg = 0;

            for (int i = 0; i < trueClasses.length; i++) {
                if (trueClasses[i] == c) {
                    if (predClasses[i] == c) {
                        truePos++;
                    } else {
                        falseNeg++;
                    }
                } else {
                    if (predClasses[i] == c) {
                        falsePos++;
                    } else {
                        trueNeg++;
                    }
                }
            }

            double recall = (double) truePos / (truePos + falseNeg);
            this.measurements.add(new Measurement("recall-" + c, recall));

            double precision = (double) truePos / (truePos + falsePos);
            this.measurements.add(new Measurement("precision-" + c, precision));

            double f1 = (2 * precision * recall) / (precision + recall);
            this.measurements.add(new Measurement("f1-" + c, f1));

            double accuracy = (double) (truePos + trueNeg) / (truePos + falseNeg + falsePos + trueNeg);
            this.measurements.add(new Measurement("accuracy-" + c, accuracy));
        }
    }
}