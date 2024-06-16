package managers;

// Удалить импорт, чтобы избежать конфликта имен
// import task1.pages.StartPage;
// import task2.pages.StartPage;

import task2.pages.GroupSchedulePage;
import task2.pages.SchedulePage;
import task4.pages.BrandsPage;
import task4.pages.DescriptionSearchPage;
import task4.pages.SearchProductsPage;

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
    private SearchProductsPage newProductsPage;
    private DescriptionSearchPage firstPromoPage;
    private task4.pages.BrandsPage brandsPage;
    private task5.pages.StartPage startPageTask5;
    private task4.pages.CategoryPage categoryPage;


    private PageManager() {

    }

    public static PageManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    public task1.pages.StartPage getStartPage_task1() {
        if (startPageTask1 == null) {
            startPageTask1 = new task1.pages.StartPage();
        }
        return startPageTask1;
    }

    public task2.pages.StartPage getStartPage_task2() {
        if (startPageTask2 == null) {
            startPageTask2 = new task2.pages.StartPage();
        }
        return startPageTask2;
    }

    public SchedulePage getStartSchedulePage() {
        if (schedulePage == null) {
            schedulePage = new task2.pages.SchedulePage();
        }
        return schedulePage;
    }

    public GroupSchedulePage getStartGroupSchedulePage() {
        if (groupSchedulePage == null) {
            groupSchedulePage = new task2.pages.GroupSchedulePage();
        }
        return groupSchedulePage;
    }

    public task3.pages.StartPage getStartPage_task3() {
        if (startPageTask3 == null) {
            startPageTask3 = new task3.pages.StartPage();
        }
        return startPageTask3;
    }

    public task3.pages.XboxPage getxboxPage() {
        if (xboxPage == null) {
            xboxPage = new task3.pages.XboxPage();
        }
        return xboxPage;
    }

    public task3.pages.FavoritePage getfavoritePage() {
        if (favoritePage == null) {
            favoritePage = new task3.pages.FavoritePage();
        }
        return favoritePage;
    }

    public task4.pages.StartPage getStartPage_task4() {
        if (startPageTask4 == null) {
            startPageTask4 = new task4.pages.StartPage();
        }
        return startPageTask4;
    }

    public SearchProductsPage getSearchProductsPage() {
        if (newProductsPage == null) {
            newProductsPage = new SearchProductsPage();
        }
        return newProductsPage;
    }

    public DescriptionSearchPage getDescriptionSearchPage() {
        if (firstPromoPage == null) {
            firstPromoPage = new DescriptionSearchPage();
        }
        return firstPromoPage;
    }

    public task4.pages.BrandsPage getBrandsPage() {
        if (brandsPage == null) {
            brandsPage = new BrandsPage();
        }
        return brandsPage;
    }

    public task5.pages.StartPage getStartPage_task5() {
        if (startPageTask5 == null) {
            startPageTask5 = new task5.pages.StartPage();
        }
        return startPageTask5;
    }

    public task4.pages.CategoryPage getCategoryPage() {
        if (categoryPage == null) {
            categoryPage = new task4.pages.CategoryPage();
        }
        return categoryPage;
    }
}
