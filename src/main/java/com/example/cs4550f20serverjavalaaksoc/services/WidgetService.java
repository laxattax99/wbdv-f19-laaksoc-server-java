package com.example.cs4550f20serverjavalaaksoc.services;

import com.example.cs4550f20serverjavalaaksoc.models.Widget;
import com.example.cs4550f20serverjavalaaksoc.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class WidgetService {
    @Autowired
    WidgetRepository widgetRepository;

    public Widget createWidget(Widget widget) {
        return widgetRepository.save(widget);
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        return widgetRepository.findWidgetsByTopicId(topicId);
    }

    public Widget updateWidget(Integer widgetId, Widget widget) {
        widget.setId(widgetId);
//        Widget widgetToUpdate = widgetRepository.findById(widgetId).get();
//        widgetToUpdate.setName(widget.getName());
//        widgetToUpdate.setTopicId(widget.getTopicId());
//        widgetToUpdate.setWidgetOrder(widget.getWidgetOrder());
//        widgetToUpdate.setCssClass(widget.getCssClass());
//        widgetToUpdate.setHeight(widget.getHeight());
//        widgetToUpdate.setWidth(widget.getWidth());
//        widgetToUpdate.setSize(widget.getSize());
//        widgetToUpdate.setStyle(widget.getStyle());
//        widgetToUpdate.setText(widget.getText());
//        widgetToUpdate.setType(widget.getType());
//        widgetToUpdate.setUrl(widget.getUrl());
//        widgetToUpdate.setValue(widget.getValue());
        return widgetRepository.save(widget);
    }

    public void deleteWidget(Integer widgetId) {
        widgetRepository.deleteById(widgetId);
    }

    public Widget findWidgetById(Integer widgetId) {
        return widgetRepository.findById(widgetId).get();
    }

    public List<Widget> findAllWidgets() {
        return (List<Widget>) widgetRepository.findAll();
    }
}
