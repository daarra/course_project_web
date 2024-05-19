package managers;

// Удалить импорт, чтобы избежать конфликта имен
// import task1.pages.StartPage;
// import task2.pages.StartPage;

import task2.pages.GroupSchedulePage;
import task2.pages.SchedulePage;

public class PageManager {
    private static PageManager INSTANCE = null;
    private task1.pages.StartPage startPageTask1;
    private task2.pages.StartPage startPageTask2;
    private task2.pages.SchedulePage schedulePage;
    private task2.pages.GroupSchedulePage groupSchedulePage;

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
}
