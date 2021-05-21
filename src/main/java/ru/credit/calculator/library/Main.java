package ru.credit.calculator.library;

import ru.credit.calculator.library.model.InitialParameters;
import ru.credit.calculator.library.service.ParsingXML;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        InitialParameters parameter = ParsingXML.unmarshalXML("src\\main\\resources\\parameters\\parameter.xml");
        ParsingXML.marshalXML(parameter,"src\\main\\resources\\result\\credits.xml" );
    }


}
