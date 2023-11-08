package testScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateCourseTest extends BaseClass {
	
	public void createCourseTest() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(course.getPageHeader().contains("course List"));
		
		course.clickNewButton();
		Thread.sleep(3000);
		soft.assertEquals(course.getPageHeader(), "Add New Course");
		Map<String, String> map = excel.readFromExcel("sheet2", "Add Course");
		String courseName = map.get("Name")+jutil.generateRandomNum(100);
		addCourse.setName(courseName);
		addCourse.selectCategory(webUtil, map.get("category"));
		addCourse.setPrice(map.get("price"));
		addCourse.uploadPhoto(map.get("photo"));
		
		addCourse.setDescription(webUtil, map.get("Description"));
		addCourse.clickSaveButton();
		soft.assertTrue(course.getSuccessMessage().contains("Success"));
		
		boolean isPresent = false;
		List<WebElement> courseNameList = course.getCourseList();
		for (WebElement name : courseNameList) {
			if (name.getText().equals(courseName)) {
				isPresent = true;
				break;
			}
		}
		
		soft.assertTrue(isPresent);
		
		course.clickDeleteButton(courseName, driver);
		course.clickDelete();
		soft.assertTrue(course.getSuccessMessage().contains("success"));
		if(course.getSuccessMessage().contains("Success"))
			excel.writeToExcel("Sheet2", "Add Course", "Pass", IConstantPath.EXCEL_PATH);
		else
			excel.writeToExcel("Sheet2", "Add Course", "Fail", IConstantPath.EXCEL_PATH);
		
		soft.assertAll();
	}

}
