package com.benjamin.myBot.commands.impl;

import com.benjamin.myBot.commands.Command;
import com.benjamin.myBot.service.CurrencyConversionService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CurrencyConversionCommand implements Command {

    private CurrencyConversionService currencyConversionService;

    @Override
    public String name() {
        return "/convert";
    }

    @Override
    public String execute(String... args){
        if(args.length == 0){
            return "Veuillez entrer les paramètres de la conversion";
        }
        //les arguments sont le montant, la devise de départ et la devise de fin
        double amount = Double.parseDouble(args[0]);
        if(args.length != 3){
            return "Veuillez entrer les paramètres de la conversion";
        }
        return args[0] + " " + args[1] + " vaut " + currencyConversionService.convert(amount, args[1], args[2]) + " " + args[2];
    }

}
