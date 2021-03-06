package ru.credit.calculator.library.service;


import ru.credit.calculator.library.application.Calculator;
import ru.credit.calculator.library.model.Credit;
import ru.credit.calculator.library.model.Credits;
import ru.credit.calculator.library.model.InitialParameters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class ParsingXML {

  public static InitialParameters unmarshalXML(String path) throws JAXBException {
    try (InputStream file = new FileInputStream(path)) {
      JAXBContext jaxbContext = null;
      try {
        jaxbContext = JAXBContext.newInstance(InitialParameters.class);
      } catch (JAXBException e) {
        e.printStackTrace();
      }
      if (jaxbContext != null) {
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (InitialParameters) jaxbUnmarshaller.unmarshal(file);
      } else {
        return new InitialParameters();
      }
    } catch (IOException e) {
      return new InitialParameters();

    }
  }


  public static void marshalXML(InitialParameters parameter, String path) throws JAXBException, FileNotFoundException {
    Credits credits = new Credits();
    List<Credit> creditList = Calculator.calculateSchedule(parameter);
    credits.setCredits(creditList);
    JAXBContext jaxbContext = JAXBContext.newInstance(Credits.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    try (FileOutputStream file = new FileOutputStream(path)) {
      jaxbMarshaller.marshal(credits, file);
    } catch (IOException e) {
      e.printStackTrace();
    }

    //jaxbMarshaller.marshal(credits, System.out); Вывод в консоль
  }


}
