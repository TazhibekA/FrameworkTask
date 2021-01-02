package test;

import  page.MainPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class SearchManagementTests extends CommonConditions {

    protected static final String SEARCHRESULTS_URL = "https://cloud.google.com/s/results?q=Google%20Cloud%20Platform%20Pricing%20Calculator";
    protected static final String SEARCHTEXT = "Google Cloud Platform Pricing Calculator";

    @Test(description = "JIRA-7566")
    public void canSearch()
    {
        String searchTextUrl = new MainPage(driver)
                .openPage()
                .search(SEARCHTEXT)
                .getCurrentUrl();

        assertThat(searchTextUrl, is(equalTo(SEARCHRESULTS_URL)));

    }

//    @Test(description = "JIRA-7567")
//    public void newProjectsAreEmpty()
//    {
//        User testUser = UserCreator.withCredentialsFromProperty();
//        String testRepositoryName = StringUtils.generateRandomRepositoryNameWithPostfixLength(REPOSITORY_NAME_POSTFIX_LENGTH);
//        boolean isCurrentRepositoryEmpty = new AnnotatedSearchPage(driver)
//                .openPage()
//                .login(testUser)
//                .invokeNewRepositoryCreation()
//                .createNewRepository(testRepositoryName, REPOSITORY_DESCRIPTION)
//                .isCurrentRepositoryEmpty();
//
//        Assert.assertTrue(isCurrentRepositoryEmpty, "newly created repository is not empty");
//    }
}
