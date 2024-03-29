package kr.co.farmstory2.db;

public class Sql {
	
	// user
	public static final String INSERT_USER = "insert into `board_user` set"
											+ "`uid`=?,"
											+ "`pass`=SHA2(?, 256),"
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`regip`=?,"
											+ "`rdate`=NOW()";
	
	public static final String SELECT_TERMS = "select * from `board_terms`";
	public static final String SELECT_USER = "select * from `board_user` where `uid`=? and `pass`=SHA2(?, 256)";
	public static final String SELECT_COUNT_UID = "select count(`uid`) from `board_user` where `uid`=?";
	public static final String SELECT_COUNT_NICK = "select count(`nick`) from `board_user` where `nick`=?";
	public static final String SELECT_COUNT_EMAIL = "select count(`email`) from `board_user` where `email`=?";
	public static final String SELECT_COUNT_HP = "select count(`hp`) from `board_user` where `hp`=?";
	public static final String SELECT_USER_FOR_FIND_ID = "select * from `board_user` where `name`=? and `email`=?";
	public static final String SELECT_USER_FOR_FIND_PW = "select * from `board_user` where `uid`=? and `email`=?";
	public static final String SELECT_USER_BY_SESSID = "SELECT * FROM `board_user` WHERE `sessId`=? AND `sessLimitDate` > NOW()";
	
	
	public static final String UPDATE_USER_PASSWORD = "update `board_user` set `pass`=SHA2(?, 256) where `uid`=?";
	public static final String UPDATE_USER_FOR_SESSION = "UPDATE `board_user` SET `sessid`=?, `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `uid`=?";
	public static final String UPDATE_USER_FOR_SESS_LIMIT_DATE = "UPDATE `board_user` SET `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `sessId`=?";
	public static final String UPDATE_USER_FOR_SESSION_OUT = "UPDATE `board_user` SET `sessId`=NULL, `sessLimitDate` =Null WHERE `uid`=?";
	
	
	// board
	public static final String INSERT_ARTICLE = "insert into `board_article` set"
												+ "`cate`=?,"
												+ "`title`=?,"
												+ "`content`=?,"
												+ "`file`=?,"
												+ "`uid`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public static final String INSERT_FILE = "insert into `board_file` set"
											+ "`parent`=?,"
											+ "`newName`=?,"
											+ "`oriName`=?";
	
	public static final String INSERT_COMMENT = "insert into `board_article` set"
												+ "`parent`=?,"
												+ "`content`=?,"
												+ "`uid`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	
	public static final String SELECT_MAX_NO = "select max(`no`) from `board_article`";

	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(`no`) FROM `board_article` WHERE `parent` = 0 AND `cate`=?";
	public static final String SELECT_COUNT_TOTAL_FOR_SEARCH = "SELECT COUNT(`no`) FROM `board_article` As a "
															+ "JOIN `board_user` AS b ON a.uid = b.uid "
															+ " WHERE `parent` = 0 and `cate`=? AND (`title` LIKE ? OR `nick` LIKE ?) ";
	
	public static final String SELECT_ARTICLES = "select a.*, b.nick from `board_article` as a "
											    + "join `board_user` as b "
											    + "on a.uid = b.uid "
											    + "where `parent` = 0 and `cate`=? "
												+ "order by a.no desc "
											    + "LIMIT ?, 10";	
	
	public static final String SELECT_ARTICLE = "SELECT a.*, b.fno, b.parent AS pno, b.newName, b.oriName, b.download "
												+ "FROM `board_article` AS a "
												+ "LEFT JOIN `board_file` AS b "
												+ "ON a.`no` = b.`parent` "
												+ "WHERE `no`=?";
	
	public static final String SELECT_ARTICLES_BY_KEYWORD = "SELECT a.*, b.nick FROM `board_article` AS a "
															+ "JOIN `board_user` AS b ON a.uid = b.uid "
															+ "WHERE `parent`=0 and `cate`=? AND (`title` LIKE ? OR `nick` LIKE ?) "
															+ "ORDER BY `no` DESC LIMIT ?, 10";

	public static final String SELECT_FILE = "select * from `board_file` where `parent`=?";
	
	public static final String SELECT_COMMENTS = "SELECT a.*, b.`nick` FROM `board_article` AS a "
												+ "JOIN `board_user` AS b "
												+ "ON a.uid = b.uid "
												+ "WHERE `parent`=? ORDER BY `no` ASC";
	
	public static final String SELECT_COMMENT_LATEST = "SELECT a.*, b.nick FROM `board_article` AS a "
														+ "JOIN `board_user` AS b using(`uid`) "
														+ "WHERE `parent`!=0 ORDER BY `no` DESC LIMIT 1 ";
													
	
	public static final String SELECT_LATESTS = "(SELECT `no`, `title`, `rdate` FROM `board_article` WHERE `cate` =? ORDER BY `no` DESC LIMIT 5) "
												+ "UNION "
												+ "(SELECT `no`, `title`, `rdate` FROM `board_article` WHERE `cate` =? ORDER BY `no` DESC LIMIT 5) "
												+ "UNION "
												+ "(SELECT `no`, `title`, `rdate` FROM `board_article` WHERE `cate` =? ORDER BY `no` DESC LIMIT 5)";
	
	
	public static final String SELECT_LATEST = "SELECT `no`, `title`, `rdate` FROM `board_article` WHERE `cate` =? ORDER BY `no` DESC LIMIT 3";
	
	// 최신글 가져오기 추가쿼리
	public static final String SELECT_LATEST_ARTICLES = "SELECT * FROM(SELECT *, ROW_NUMBER() OVER(PARTITION BY `cate` ORDER BY `no` DESC) a FROM `board_article`) rankrow "
													+ "WHERE rankrow.a <= 5 AND `cate` IN ('croptalk1', 'croptalk2', 'croptalk3')";
	
	public static final String UPDATE_ARTICLE = "update `board_article` set `title`=?, `content`=?, `rdate`=NOW() where `no`=?";
	
	public static final String UPDATE_ARTICLE_HIT = "UPDATE `board_article` SET `hit` = `hit` + 1 WHERE `no`=?";
	
	public static final String UPDATE_FILE_DOWNLOAD = "UPDATE `board_file` SET `download` = `download` + 1 WHERE `fno`=?";
	
	public static final String UPDATE_ARTICLE_COMMENT_PLUS = "update `board_article` set `comment` = `comment` + 1 where `no`=?";
	
	public static final String UPDATE_ARTICLE_COMMENT_MINUS = "update `board_article` set `comment` = `comment` - 1 where `no`=?";
	
	public static final String UPDATE_COMMENT = "update `board_article` set "
												+ "`content`=?,"
												+ "`rdate`=NOW() "
												+ "where `no`=?";

	public static final String DELETE_ARTICLE = "delete from `board_article` where `no`=? or `parent`=?";
	
	public static final String DELETE_COMMENT = "delete from `board_article` where `no`=?";
	
	public static final String DELETE_FILE = "delete from `board_file` where `parent`=?";
	
}
