package com.demo.blaze.logic;

import com.demo.blaze.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BlazeProductListLogic extends Page {

    By navLOGIN = By.id("login2");
    By txtUSERNAME = By.id("loginusername");
    By txtPASSWORD = By.id("loginpassword");
    By btnLOGIN = By.cssSelector("#logInModal .btn-primary");
    By lblMONITOR = By.linkText("Monitors");
    By lblPHONE = By.linkText("Phones");
    By PRODUCTRESULT = By.tagName("a");
    By PRODUCTPRICE  = By.tagName("h5");

    public void displayPage(){
        try{
            WebDriverManager.navigateTo("https://www.demoblaze.com/");
            staticWait(2);
            System.out.println("*** WebDriver Opened URL ***");
        }catch (Exception e) {
            System.out.println("*** Error Opening URL displayPage()"+e+" ***");
        }
    }
    public void userInputCredential(String user, String pass){
        try{
            WebDriverManager.getDriver().findElement(navLOGIN).click();
            staticWait(2);
            WebDriverManager.getDriver().findElement(txtUSERNAME).click();
            WebDriverManager.getDriver().findElement(txtUSERNAME).sendKeys(user);
            staticWait(2);
            WebDriverManager.getDriver().findElement(txtPASSWORD).click();
            WebDriverManager.getDriver().findElement(txtPASSWORD).sendKeys(pass);
            staticWait(2);
            WebDriverManager.getDriver().findElement(btnLOGIN).click();
            staticWait(2);
            System.out.println("*** User Input Credential ***");
        }catch (Exception e) {
            System.out.println("*** Error with InputCredential(): "+e+" ***");
        }
    }
    public void selectMonitorCategory(){
        try{
            WebDriverManager.getDriver().findElement(lblMONITOR).click();
            staticWait(2);
            System.out.println("*** User Click on Monitor Category ***");
        }catch (Exception e) {
            System.out.println("*** Error with selectMonitorCategory(): "+e+" ***");
        }
    }
    public void verifyReturnedItems(){
        try{
            //Looking for Price
            List<WebElement> priceResults = WebDriverManager.getDriver().findElements(PRODUCTPRICE);
            priceResults.removeIf(x->x.getText()== "" );
            Set<WebElement> uniquePrice = priceResults
                    .stream() // get stream for original list
                    .collect(Collectors.toCollection(//distinct elements stored into new SET
                            () -> new TreeSet<>(Comparator.comparing(x->x.getText())))
                    );
            //Looking for Product List
            List<WebElement> searchResults = WebDriverManager.getDriver().findElements(PRODUCTRESULT);
            searchResults.removeIf(x->x.getAttribute("href")=="" || x.getText()== "" || x.getAttribute("href").contains("#") || x.getText().contains("CATEGORIES")|| x.getAttribute("href").contains("index") || x.getAttribute("href").contains("cart"));
            Set<WebElement> uniqueLinksSet = searchResults
                    .stream() // get stream for original list
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(x->x.getAttribute("href"))))
                    );
            int i=1    ;
            for ( WebElement price : uniquePrice){
                String priceUnit = price.getText();
                for ( WebElement item : uniqueLinksSet)
                {
                    System.out.println("Item#"+ i +" - "+ item.getText() +" - "+ priceUnit +" - "+ item.getAttribute("href"));
                }
                i++;
            }
            staticWait(5);
        }catch (Exception e) {
            System.out.println("*** Error with verifyReturnedItems(): "+e+" ***");
        }
    }
    public void selectPhoneCategory(){
        try{
            WebDriverManager.getDriver().findElement(lblPHONE).click();
            staticWait(2);
            System.out.println("*** User Click on Phones Category ***");
        }catch (Exception e) {
            System.out.println("*** Error with selectPhoneCategory(): "+e+" ***");
        }
    }
    public void extractResultAssTXT(){
        try {
            System.out.println("*** Before Extract Result ***");
            List<WebElement> allLinks = WebDriverManager.getDriver().findElements(PRODUCTRESULT);
            allLinks.removeIf(x->x.getAttribute("href")=="" || x.getText()== "" || x.getAttribute("href").contains("#") || x.getText().contains("CATEGORIES")|| x.getAttribute("href").contains("index") || x.getAttribute("href").contains("cart"));
            Set<WebElement> uniqueLinksSet = allLinks
                    .stream() // get stream for original list
                    .collect(Collectors.toCollection(//distinct elements stored into new SET
                            () -> new TreeSet<>(Comparator.comparing(x->x.getAttribute("href"))))
                    );
            String path = System.getProperty("user.dir");
            String directoryPath = path+"/src/test/resources/resultTest";
            File directoryFile = new File(directoryPath);

            if(!directoryFile.exists())
                directoryFile.mkdir();

            File myObj = new File(directoryPath+"/result.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(directoryPath+"/result.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(myWriter);

            int i= 0;
            for ( WebElement item : uniqueLinksSet)
            {
                i++;
                if(item != null )
                {

                    bufferedWriter.write("Item#"+i+"-" + item.getText() +" - "+ item.getAttribute("href"));
                    bufferedWriter.newLine();
                    System.out.println("Item#"+i+"-" + item.getText()+" - "+ item.getAttribute("href"));
                }
                if(i==10)
                    break;
            }
            bufferedWriter.close();
            myWriter.close();
        }
        catch (Exception e) {
            System.out.println("*** Error with extractResultAssTXT(): "+e+" ***");
        }
    }
    public void verifyResultGenerated( String Text){
        BufferedReader objReader = null;
        String expectedText = Text;
        try {
            String strCurrentLine;
            String path = System.getProperty("user.dir");
            String directoryPath = path+"/src/test/resources/resultTest";
            objReader = new BufferedReader(new FileReader(directoryPath+"/result.txt"));
            while ((strCurrentLine = objReader.readLine()) != null) {
                Assert.assertFalse(strCurrentLine.contains(expectedText));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (objReader!= null)
                    objReader.close();
            } catch (IOException e) {
                System.out.println("*** Error with verifyResultGenerated(): "+e+" ***");
            }
        }
    }

}