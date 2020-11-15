package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {



	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void TestUnauthorizedAcess() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/result");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void TestAuthorizedAcess() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port + "/login");
		this.driver.findElement(By.id("inputUsername")).sendKeys("cris");
		this.driver.findElement(By.id("inputPassword")).sendKeys("master");
		this.driver.findElement(By.id("LoginButton")).click();
		Assertions.assertEquals("Home", driver.getTitle());
		Thread.sleep(3_000);
		this.driver.findElement(By.id("LogoutButton")).click();
		this.driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		Thread.sleep(3_000);
	}

	@Test
	public void TestSignUpAccess() throws InterruptedException {

	}



}
