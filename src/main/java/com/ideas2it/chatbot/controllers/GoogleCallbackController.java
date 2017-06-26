/*
 *
 */
package com.ideas2it.chatbot.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.chatbot.service.GoogleConnectionService;

@RestController
public class GoogleCallbackController extends BaseController {

    @Autowired
    private GoogleConnectionService connection;

    @RequestMapping(value = "/oauth2callback", method = RequestMethod.GET)
    public void callback(@RequestParam("code")
    final String code, final HttpServletResponse response) throws IOException {
        if (connection.exchangeCode(code))
            response.sendRedirect(connection.getSourceUrl());
        else
            response.sendRedirect("/error");
    }
}
