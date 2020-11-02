package com.example.cs4550f20serverjavalaaksoc.services;

import com.example.cs4550f20serverjavalaaksoc.models.Widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WidgetService {
    private HashMap<String, Widget> widgetMap = new HashMap<String, Widget>();

    {
        widgetMap.put("123", new Widget("123", "HTML", "Widget 1", "topic123"));
        widgetMap.put("234", new Widget("234", "YOU_TUBE", "Widget 2", "topic123"));
        widgetMap.put("345", new Widget("345", "SLIDE", "Widget 3", "topic123"));
        widgetMap.put("456", new Widget("456", "HEADER", "Widget 4", "topic123"));
        widgetMap.put("567", new Widget("567", "HEADING", "Widget A", "topic234"));
        widgetMap.put("678", new Widget("678", "PARAGRAPH", "Widget B", "topic234"));
    }

    public Widget createWidget(String topicId, Widget widget) {
        String newId = UUID.randomUUID().toString().replace("-", "");
        widget.setId(newId);
        widget.setTopicId(topicId);
        widget.setWidgetOrder(findWidgetsForTopic(topicId).size());
        widgetMap.put(newId, widget);
        return widget;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        List<Widget> listCopy = new ArrayList<Widget>(widgetMap.values());
        listCopy.removeIf(widget -> !widget.getTopicId().equals(topicId));
        return listCopy;
    }

    public int updateWidget(String widgetId, Widget widget) {
        Widget oldWidget = widgetMap.put(widgetId, widget);
        if (oldWidget != null) {
            return 0;
        } else {
            return 1;
        }
    }

    public int deleteWidget(String widgetId) {
        Widget oldWidget = widgetMap.remove(widgetId);
        if (oldWidget != null) {
            return 0;
        } else {
            return 1;
        }
    }

    public Widget findWidgetById(String widgetId) {
        return widgetMap.get(widgetId);
    }

    public List<Widget> findAllWidgets() {
        return new ArrayList<>(widgetMap.values());
    }
}
