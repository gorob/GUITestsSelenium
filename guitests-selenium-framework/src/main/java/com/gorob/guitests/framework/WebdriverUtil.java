package com.gorob.guitests.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebdriverUtil {
    private int serverPort;
    private String testName;

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    private int getServerPort() {
        return serverPort;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    private String getBaseUrl(){
        return "http://localhost:" + getServerPort();
    }

    public WebDriver getWebDriver(){
        return Driver.getWebDriver();
    }

    public void initWindow() {
        maximizeWindow();
    }

    public void setWindowTitle(){
        ((JavascriptExecutor) getWebDriver()).executeScript("document.title = '" + getTestName() + "'");
    }

    public void navigateTo(String relativePath){
        getWebDriver().get(getBaseUrl() + relativePath);
    }

    private void maximizeWindow(){
        getWebDriver().manage().window().maximize();
    }

    private WebElement getHead(){
        return getWebDriver().findElement(By.tagName("head"));
    }

    public String getWindowTitle(){
        return getWebDriver().getTitle();
    }

    private WebElement getTable(String tableId){
        WebElement webElement = getWebDriver().findElement(By.id(tableId));
        return webElement.getTagName().equals("table") ? webElement : null;
    }

    private List<WebElement> getTableHeaderColumns(String tableId){
        WebElement headerElement = getTable(tableId).findElement(By.tagName("thead"));
        WebElement headerRowElement = headerElement.findElement(By.tagName("tr"));
        return headerRowElement.findElements(By.tagName("th"));
    }

    private WebElement getTableHeaderColumn(String tableId, int colIndex){
        List<WebElement> tableHeaderColumns = getTableHeaderColumns(tableId);
        return tableHeaderColumns.isEmpty() ? null : tableHeaderColumns.get(colIndex-1);
    }

    public String getTableHeaderColumnValue(String tableId, int colIndex){
        WebElement tableHeaderColumn = getTableHeaderColumn(tableId, colIndex);
        return tableHeaderColumn==null ? null : tableHeaderColumn.getText();
    }

    public WebElement getTableHeaderLink(String tableId, int colIndex){
        WebElement tableCellElement = getTableHeaderColumn(tableId, colIndex);
        List<WebElement> linkElements = tableCellElement.findElements(By.tagName("a"));
        return linkElements.isEmpty() ? null : linkElements.get(0);
    }

    public void clickTableHeaderLink(String tableId, int colIndex) {
        getTableHeaderLink(tableId, colIndex).click();
    }

    private List<WebElement> getTableRows(String tableId){
        WebElement bodyElement = getTable(tableId).findElement(By.tagName("tbody"));
        return bodyElement.findElements(By.tagName("tr"));
    }

    private WebElement getTableRow(String tableId, int rowIndex){
        List<WebElement> tableRows = getTableRows(tableId);
        return tableRows.isEmpty() ? null : tableRows.get(rowIndex-1);
    }

    private List<WebElement> getTableColumnsOfRow(String tableId, int rowIndex){
        WebElement tableRowElement = getTableRow(tableId, rowIndex);
        if (tableRowElement==null){ return null; }
        return tableRowElement.findElements(By.tagName("td"));
    }

    public WebElement getTableCell(String tableId, int rowIndex, int colIndex){
        List<WebElement> tableColumnsOfRow = getTableColumnsOfRow(tableId, rowIndex);
        return (tableColumnsOfRow==null || tableColumnsOfRow.isEmpty()) ? null : tableColumnsOfRow.get(colIndex-1);
    }

    public WebElement getTableCellButton(String tableId, int rowIndex, int colIndex){
        WebElement tableCellElement = getTableCell(tableId, rowIndex, colIndex);
        List<WebElement> buttonElements = tableCellElement.findElements(By.tagName("button"));
        return buttonElements.isEmpty() ? null : buttonElements.get(0);
    }

    public WebElement getTableCellLink(String tableId, int rowIndex, int colIndex){
        WebElement tableCellElement = getTableCell(tableId, rowIndex, colIndex);
        List<WebElement> linkElements = tableCellElement.findElements(By.tagName("a"));
        return linkElements.isEmpty() ? null : linkElements.get(0);
    }

    public void clickTableCellButton(String tableId, int rowIndex, int colIndex) {
        getTableCellButton(tableId, rowIndex, colIndex).click();
    }

    public void clickTableCellLink(String tableId, int rowIndex, int colIndex) {
        getTableCellLink(tableId, rowIndex, colIndex).click();
    }

    public String getTableCellValue(String tableId, int rowIndex, int colIndex){
        WebElement tableCellElement = getTableCell(tableId, rowIndex, colIndex);
        return tableCellElement==null ? null : tableCellElement.getText();
    }

    public int getNumberOfRowsInTable(String tableId){
        return getTableRows(tableId).size();
    }

    public int getNumberOfHeaderColumnsInTable(String tableId){
        return getTableHeaderColumns(tableId).size();
    }

    private boolean isInputWithType(WebElement webElement, String expectedType){
        if (!webElement.getTagName().equals("input")) { return false; }
        return (webElement.getAttribute("type")==null && expectedType==null)
                || webElement.getAttribute("type").equals(expectedType);
    }

    private boolean isLink(WebElement webElement){
        return webElement.getTagName().equals("a");
    }

    private boolean isButtonOrLink(WebElement webElement){
        return isButton(webElement) || isLink(webElement);
    }

    private boolean isButton(WebElement webElement){
        return isInputWithType(webElement, "submit");
    }

    private boolean isCombobox(WebElement webElement){
        return webElement.getTagName().equals("select");
    }

    private boolean isTextfield(WebElement webElement){
        return isInputWithType(webElement, null)
            || isInputWithType(webElement, "text")
            || isInputWithType(webElement, "hidden");
    }

    private boolean isLabelAndHasCorrespondingField(WebElement webElement, String correspondingFieldId){
        return webElement.getTagName().equals("label") && webElement.getAttribute("for").equals(correspondingFieldId);
    }

    public WebElement getCombobox(String comboboxId) {
        WebElement webElement = getWebDriver().findElement(By.id(comboboxId));
        return isCombobox(webElement) ? webElement : null;
    }

    public String getComboboxValue(String comboboxId){
        List<WebElement> options = getCombobox(comboboxId).findElements(By.tagName("option"));
        for (WebElement option : options) {
            String selectedStr = option.getAttribute("selected");
            if (selectedStr!=null && !selectedStr.equals("")){
                return option.getAttribute("value");
            }
        }
        return null;
    }

    public WebElement getTextfield(String textfieldId){
        WebElement webElement = getWebDriver().findElement(By.id(textfieldId));
        return isTextfield(webElement) ? webElement : null;
    }

    public String getTextfieldValue(String textfieldId){
        return getTextfield(textfieldId).getAttribute("value");
    }

    public WebElement getButton(String buttonId){
        WebElement webElement = getWebDriver().findElement(By.id(buttonId));
        return isButtonOrLink(webElement) ? webElement : null;
    }

    public WebElement getLabel(String correspondingFieldId) {
        return getWebDriver().findElement(By.xpath("//label[@for='" + correspondingFieldId + "']"));
    }

    public String getLabelText(String correspondingFieldId) {
        return getLabel(correspondingFieldId).getText();
    }

    public String getButtonLabelText(String buttonId){
        WebElement buttonElement = getButton(buttonId);
        if (isLink(buttonElement)){
            return buttonElement.getText();
        }
        return buttonElement.getAttribute("value");
    }

    public String getErrorMessage(String errorBlockId){
        List<WebElement> errorBlockElements = getWebDriver().findElements(By.id(errorBlockId));
        if (errorBlockElements.isEmpty()) { return null; }

        List<WebElement> errorMessageListElements = errorBlockElements.get(0).findElements(By.tagName("li"));

        StringBuffer errorMessageBuffer = new StringBuffer();

        for (WebElement errorMessageListElement : errorMessageListElements) {
            errorMessageBuffer.append(errorMessageListElement.getText());
            errorMessageBuffer.append(System.lineSeparator());
        }

        if (errorMessageBuffer.length()==0){ return null; }

        String errorMessageText = errorMessageBuffer.toString();

        return errorMessageText.substring(0, errorMessageText.length()-System.lineSeparator().length());
    }


}