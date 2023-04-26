/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 *
 * @author Harmony
 */
public class Trainer {

    
    

    public static void trainCitation() {
        
        File citation_file = new File("C:" + File.separator + "Users" + File.separator + "Harmony" + File.separator + "Documents" + File.separator + "xcm" + File.separator + "ALL MATERIALS" + File.separator + "en-sentiment3.train");

        String modelFileName = "en-sentiment3.bin";

        File citationModelFile = new File("C:" + File.separator + "Users" + File.separator + "Harmony" + File.separator + "Documents" + File.separator + "xcm" + File.separator + "ALL MATERIALS" + File.separator + modelFileName);

        try {

            DoccatModel model = null;
            DoccatFactory df = new DoccatFactory();

            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(citation_file);

            System.out.println("This is cyberBullyModel method, file1 path is " + citationModelFile.getPath());

            ObjectStream lineStream = new PlainTextByLineStream(dataIn, StandardCharsets.UTF_8);

            ObjectStream sampleStream = new DocumentSampleStream(lineStream);

            TrainingParameters params = new TrainingParameters();

            params.put(TrainingParameters.ITERATIONS_PARAM, 10 + "");
            params.put(TrainingParameters.CUTOFF_PARAM, 0 + "");
            //params.put(AbstractTrainer.ALGORITHM_PARAM,NaiveBayesTrainer.NAIVE_BAYES_VALUE);
            //params.put(AbstractTrainer.ALGORITHM_PARAM,GISTrainer.MAXENT_VALUE);

            try {
                model = DocumentCategorizerME.train("en", sampleStream, params, df);
                //model = DocumentCategorizerME.train("en", sampleStream,TrainingParameters.defaultParams(),df);

            } catch (Exception e) {
                e.printStackTrace();
            }

            OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(citationModelFile));

            model.serialize(modelOut);

        } catch (Exception e) {
        }
    }
    
    public static void trainAuthor() throws FileNotFoundException, IOException{
        
        File author_file = new File("C:" + File.separator + "Users" + File.separator + "Harmony" + File.separator + "Documents" + File.separator + "xcm" + File.separator + "ALL MATERIALS" + File.separator + "tdgauthorname.train");
        String modelFileName = "authorNameModel.bin";
        File authorModelFile = new File("C:" + File.separator + "Users" + File.separator + "Harmony" + File.separator + "Documents" + File.separator + "xcm" + File.separator + "ALL MATERIALS" + File.separator + modelFileName);
        ObjectStream<String> lineStream = null ;
        TokenNameFinderModel model = null;
        TokenNameFinderFactory nameFinderFactory = new TokenNameFinderFactory();
        
        OutputStream modelOutputStream = new BufferedOutputStream(new FileOutputStream(authorModelFile));
        
        try {
            
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(author_file);
            
            lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            
        } catch (IOException ex) {
           // Handle exception
        }
        
        try (ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream)) {
            
            TrainingParameters params = new TrainingParameters();
            
            params.put(TrainingParameters.ITERATIONS_PARAM, 100 + "");
            params.put(TrainingParameters.CUTOFF_PARAM, 5 + "");
            
            model = NameFinderME.train("en", "person", sampleStream, params, nameFinderFactory);

        }       catch (IOException ex) {
            Logger.getLogger(Trainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            model.serialize (modelOutputStream);
        
    }

    public static void main(String[] args) throws IOException {

        Trainer.trainCitation();
        //Trainer.trainAuthor();
    }
}
