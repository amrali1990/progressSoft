package com.progresssoft.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.progresssoft.service.DealsServiceTest;
import com.progresssoft.service.FilesServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ DealsDaoTest.class, FilesDaoTest.class ,DealsServiceTest.class, FilesServiceTest.class})
public class AllTests {

}
