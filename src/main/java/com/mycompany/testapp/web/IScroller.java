/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;



import java.util.List;
import javax.faces.component.html.HtmlDataTable;


/**
 *
 * @author www
 */
public interface IScroller<T> {
    /**
     * Обновить данные скроллера
     */
    public void refresh();
    /**
     * Почистить фильтры
     */
    public void clear();

    /**
     * Добавить элемент
     */
    String addItemListener();

    /**
     * Редактировать элемент
     */
    void editItemListener();

    void deleteItemListener();

    void onBtnClickSortListener();

    String getFormName();

   

    HtmlDataTable getDataTable();

    void setDataTable(HtmlDataTable dataTable);

    List<T> getScrollerData();

    void setScrollerData(List<T> scrollerData);

    public T getSelectedItem();

    void setSelectedItem(T selectedItem);
}

