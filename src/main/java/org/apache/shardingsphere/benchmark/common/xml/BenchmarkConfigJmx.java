package org.apache.shardingsphere.benchmark.common.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BenchmarkConfigJmx {
    
    public static FileFilter jmxFilter = new JmxFileFilter();
    
    public static void modifyBenchmarkOutputBasePath(String benchmarkBasePath, String benchmarkOutputBasePath){
        File jmxFile = null;
        
        List<File> jmxFileList = getJmxFileList(benchmarkBasePath);
        for (int i = 0; i < jmxFileList.size(); i++){
            jmxFile = jmxFileList.get(i);
            Document xmlDocument = getOutputElement(jmxFile, benchmarkOutputBasePath);
            saveJmxFile(jmxFile, xmlDocument);
        }
    }

    
    public static List<File> filterTargetFiles(File targetDir, FileFilter filter, List<File> resultFiles) {
        
        File[] files = targetDir.listFiles(filter);
        for (File file : files) {
            if (file.isDirectory()) {
                filterTargetFiles(file, filter, resultFiles);
            } else {
                resultFiles.add(file);
            }
        }
        return resultFiles;
    }
    
    
    public static List<File> getJmxFileList(String benchmarkBasePath){
        
        List<File> jmxFileList = new ArrayList<File>();
        String jmxBasePath = benchmarkBasePath + "/src/main/resources/testplan";
        File jmxBaseDir = new File(jmxBasePath);
        
        return filterTargetFiles(jmxBaseDir, jmxFilter, jmxFileList);
    }
    
    
    public static void saveJmxFile(File testPlanFile, Document xmlDocument){
        
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source = new DOMSource();
            source.setNode(xmlDocument);
            StreamResult result = new StreamResult();
            result.setOutputStream(new FileOutputStream(testPlanFile));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {}
    
    }
    
    
    public static Document getOutputElement(File testPlanFile, String outPutBasePath){
        
        Element root = null;
        Document xmlDocument = null;
        DocumentBuilder documentBuilder = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        
        try {
            documentBuilder = factory.newDocumentBuilder();
            xmlDocument = (Document) documentBuilder.parse(testPlanFile);
            root = xmlDocument.getDocumentElement();
    
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            String resultCollectorXpath = "//jmeterTestPlan/hashTree/hashTree/hashTree/hashTree/ResultCollector";
            NodeList nodeList = (NodeList) xpath.evaluate(resultCollectorXpath, root, XPathConstants.NODESET);
            Element resultCollectorElement = (Element) nodeList.item(1);
            String stringPropXpath = "./stringProp";
            Element stringPropElement = (Element) xpath.evaluate(stringPropXpath, resultCollectorElement, XPathConstants.NODE);
            String outputPath = stringPropElement.getTextContent();
            outputPath = outputPath.replace("/basepath", outPutBasePath);
            stringPropElement.setTextContent(outputPath);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            
        }
        
        return xmlDocument;
    }
    
    
    private static class JmxFileFilter implements FileFilter {
        
        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            String fileName = file.getName();
            return fileName.matches("(?i).+jmx$");
        }
        
    }
    
    
    
    public static void main(String[] args) {
        
        String benchmarkBasePath = "D:/shardingsphere-benchmark";
        String outputBasePath = "/export/shardingsphere-benchmark/result";
        modifyBenchmarkOutputBasePath(benchmarkBasePath, outputBasePath);
        
    }
    
}