package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	private final String username = "vvvvv";
	private final String password = "vvvvvv";
	private final String name = "cris";
	private String last = "bertolini";

	@LocalServerPort
	private int port;

	private WebDriver driver;
	public String baseURL;

	@BeforeAll
	static void beforeAll() {

		WebDriverManager.chromedriver().setup();

	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.baseURL = "http://localhost:" + port;
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
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
	@Order(2)
	public void TestSignUpAccess() throws InterruptedException {
		driver.get(baseURL + "/signup");
		Signup signup = new Signup(driver);
		signup.signup(name,last,username,password);
		Thread.sleep(3_000);
		WebElement message = driver.findElement(By.id("signupSuccessMsg"));
		Assertions.assertEquals("You successfully signed up! Please sign in below.", message.getText());
		Thread.sleep(3_000);
		Login login = new Login(driver);
		login.login(username, password);
		Assertions.assertEquals("Home", driver.getTitle());
		Thread.sleep(3_000);
		driver.get(baseURL + "/invalid");
		WebElement message2 = driver.findElement(By.id("msg"));
		Assertions.assertEquals("The requested resource could not be found!", message2.getText());
		Thread.sleep(3_000);
		driver.get(baseURL + "/home");
		this.driver.findElement(By.id("LogoutButton")).click();
		WebElement errorMessage = driver.findElement(By.id("logout-msg"));
		Assertions.assertEquals("Login", driver.getTitle());
		Assertions.assertEquals("You have been logged out", errorMessage.getText());
		Thread.sleep(3_000);
		driver.get(baseURL + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		Thread.sleep(3_000);
	}

	@Test
	@Order(3)
	public void TestAuthorizedAcess() throws InterruptedException {
		driver.get(baseURL + "/login");
		Login login = new Login(driver);
		login.login(username, password);
		Assertions.assertEquals("Home", driver.getTitle());
		Thread.sleep(3_000);
		this.driver.findElement(By.id("LogoutButton")).click();
		WebElement errorMessage = driver.findElement(By.id("logout-msg"));
		Assertions.assertEquals("Login", driver.getTitle());
		Assertions.assertEquals("You have been logged out", errorMessage.getText());
		Thread.sleep(3_000);
	}



	@Test
	@Order(4)
	public void TestNoteAddUptDelete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		driver.get(baseURL + "/login");
		Login loginPage = new Login(driver);
		loginPage.login(username, password);

		Assertions.assertEquals("Home", driver.getTitle());

		Thread.sleep(3_000);
		Note notesTabPage = new Note(driver);
		Thread.sleep(3_000);
		notesTabPage.navNotes(driver);

		notesTabPage.createNote("My Test Note", " My Test Description");

		notesTabPage.navNotes(driver);
		notesTabPage.editNote("My Editied Test Note", "My Edited Test Description");

		notesTabPage.navNotes(driver);
		notesTabPage.deleteNote();

	}

	@Test
	@Order(5)
	public void TestCredentialAddUptDelete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		driver.get(baseURL + "/login");
		Login loginPage = new Login(driver);
		loginPage.login(username, password);

		Assertions.assertEquals("Home", driver.getTitle());
		Thread.sleep(3_000);
		Credential credential = new Credential(driver);
		Thread.sleep(3_000);

		credential.navCredentials(driver);

		credential.createCredential("bbbbbbb", "bbbbbbbb", "bbbbbbbb");
		credential.navCredentials(driver);
		credential.editCredential("aaaaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa");

		credential.navCredentials(driver);
		credential.deleteCredential();


	}


}
