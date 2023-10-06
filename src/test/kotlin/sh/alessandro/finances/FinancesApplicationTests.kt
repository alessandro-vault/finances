package sh.alessandro.finances

import org.junit.platform.suite.api.SelectPackages
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName
@Suite
@SuiteDisplayName("Finances Api Suite")
@SelectPackages("sh.alessandro.finances.api")
class FinancesApplicationTests
