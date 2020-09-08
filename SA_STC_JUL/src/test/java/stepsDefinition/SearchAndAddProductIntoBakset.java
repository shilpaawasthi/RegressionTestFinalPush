package stepsDefinition;

import org.junit.Assert;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.BaseUtil;
import util.Constants;


public class SearchAndAddProductIntoBakset {
	
	//Step layer
	public BaseUtil base;

	public  SearchAndAddProductIntoBakset(BaseUtil base) {
		this.base = base;
	}
	
	@Given("^that user is on the search page$")
	public void that_user_is_on_the_search_page() {
		 try {
		    	base.log.info("Get to Search Page");
		    	
		    	// Get to Search page
		    	base.keyword.getToSearchPage();
		    	base.keyword.waitUntilPageLoadsComplelety();
		    	
		    	// check element exists
		    	boolean result = base.keyword.checkElementExists("searchPageLabel");
				Assert.assertTrue(result);
				base.log.info("User is on the Search page");
			}  catch (Exception e) {
				base.log.fatal("Exception - User is NOT on the Search page");
				Assert.fail(e.getMessage());
			} catch (AssertionError e) {
				base.log.error("AssertionError - User is NOT on the Search page");
				Assert.fail(e.getMessage());
			}
	}


	@When("^the user searches for \"([^\"]*)\"$")
	public void the_user_searches_for(String product) throws Throwable {
			
			//String object = "searchLocationText";
			//String searchButton = "searchButton";
			
			base.log.info("* When the user enters " + product + " into " + "searchProductText");
			
			base.keyword.waitUntilElementIsVisible("searchProductText");
			base.keyword.type("searchProductText", product );
			base.keyword.sleep(Constants.sleepTimeOneSecond);

			// Click option from the auto complete suggested products
			
			//base.keyword.waitUntilElementIsVisible("autoCompleteSearchLocationLink");
			//base.keyword.click("autoCompleteSearchLocationLink");
			//base.keyword.sleep(Constants.sleepTimeThreeSeconds);
			base.keyword.getElement("searchButton").click();
			base.keyword.sleep(Constants.sleepTimeThreeSeconds);
				
			// check next page
			
		    base.keyword.waitUntilPageLoadsComplelety();
	    	base.keyword.checkElementExists("headerTitleLabel");
	    	base.keyword.sleep(Constants.sleepTimeThreeSeconds);
	    	 
	    	base.keyword.waitUntilPageLoadsComplelety();
		    base.keyword.checkElementExists("selectedProductNameBasedOnSearchTerm");
		    base.keyword.sleep(Constants.sleepTimeThreeSeconds);
		    	
	}

	@And("^adds product to the basket$")
	public void adds_product_to_the_basket() throws Throwable {
		
		  //click add button and check cart icon 
		
		 //String productAddButtonObject ="productAddButton";
		 
		 base.keyword.waitUntilPageLoadsComplelety();
		 base.keyword.getElement("selectedProductAddButton").click();
	     base.keyword.sleep(Constants.sleepTimeThreeSeconds);
	     
	     base.keyword.waitUntilPageLoadsComplelety();
	    // base.keyword.checkElementExists("viewBasketIconOfAddedSearchProduct");
	     base.keyword.sleep(Constants.sleepTimeThreeSeconds);
	    
	}

	@Then("^the added \"([^\"]*)\" should be available in the basket$")
	public void the_added_should_be_available_in_the_basket(String string) throws Throwable  {
		
		
		//String expected = "AddedProductViewBasket";
		
    	// Get to Basket page
    	base.keyword.getToBasket();
    	base.keyword.waitUntilPageLoadsComplelety();
    	
    	
	    boolean actual = base.keyword.checkElementExists("searchProductAtBasketSummary");
	    //base.keyword.waitUntilPageLoadsComplelety();
	    base.keyword.sleep(Constants.sleepTimeThreeSeconds);
	    //System.out.println(actual);
	    
	    Assert.assertEquals(true, actual);
	    
	    
	} 
}	


