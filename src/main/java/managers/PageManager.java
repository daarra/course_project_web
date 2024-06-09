package managers;

// Удалить импорт, чтобы избежать конфликта имен
// import task1.pages.StartPage;
// import task2.pages.StartPage;

import task2.pages.GroupSchedulePage;
import task2.pages.SchedulePage;
import task4.pages.NewProductsPage;
import task4.pages.PromotionsPage;

public class PageManager {
    private static PageManager INSTANCE = null;
    private task1.pages.StartPage startPageTask1;
    private task2.pages.StartPage startPageTask2;
    private task2.pages.SchedulePage schedulePage;
    private task2.pages.GroupSchedulePage groupSchedulePage;
    private task3.pages.StartPage startPageTask3;
    private task3.pages.XboxPage xboxPage;
    private task3.pages.FavoritePage favoritePage;
    private task4.pages.StartPage startPageTask4;
    private task4.pages.NewProductsPage newProductsPage;
    private task4.pages.PromotionsPage promotionsPage;

    private PageManager(){

    }

    public static PageManager getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    public task1.pages.StartPage getStartPage_task1(){
        if (startPageTask1 == null){
            startPageTask1 = new task1.pages.StartPage();
        }
        return startPageTask1;
    }

    public task2.pages.StartPage getStartPage_task2(){
        if (startPageTask2 == null){
            startPageTask2 = new task2.pages.StartPage();
        }
        return startPageTask2;
    }

    public SchedulePage getStartSchedulePage(){
        if (schedulePage == null){
            schedulePage = new task2.pages.SchedulePage();
        }
        return schedulePage;
    }

    public GroupSchedulePage getStartGroupSchedulePage(){
        if (groupSchedulePage == null){
            groupSchedulePage = new task2.pages.GroupSchedulePage();
        }
        return groupSchedulePage;
    }

    public task3.pages.StartPage getStartPage_task3(){
        if (startPageTask3 == null){
            startPageTask3 = new task3.pages.StartPage();
        }
        return startPageTask3;
    }

    public task3.pages.XboxPage getxboxPage(){
        if (xboxPage == null){
            xboxPage = new task3.pages.XboxPage();
        }
        return xboxPage;
    }

    public task3.pages.FavoritePage getfavoritePage(){
        if (favoritePage == null){
            favoritePage = new task3.pages.FavoritePage();
        }
        return favoritePage;
    }

    public task4.pages.StartPage getStartPage_task4(){
        if (startPageTask4 == null){
            startPageTask4 = new task4.pages.StartPage();
        }
        return startPageTask4;
    }

    public task4.pages.NewProductsPage getNewProductsPage(){
        if (newProductsPage == null){
            newProductsPage = new NewProductsPage();
        }
        return newProductsPage;
    }

    public task4.pages.PromotionsPage getpromotionsPage(){
        if ( promotionsPage == null){
            promotionsPage = new PromotionsPage();
        }
        return  promotionsPage;
    }


}
