package com.ex.application;

import com.ex.databaseUtils.HibernateUtil;
import com.ex.model.DifficultyLevel;
import com.ex.model.GeoCashe;
import com.ex.model.GeoCasheHistorys;
import com.ex.model.Item;
import com.ex.services.GpsService;
import org.hibernate.Session;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GpsService service = new GpsService();
        GeoCashe cashe = service.findCasheByID(16);
        Item item = new Item("itemName","description","image");

        GeoCasheHistorys history = new GeoCasheHistorys("person1@email.com",cashe,LocalDateTime.now(),"put it!",3);

        service.placeItem(item,history);
    }
}