package db;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

public class MemberService {
	public void resister(List<Info> list) throws IOException, ParseException {

		String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "pass";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			for (int i = 0; i < list.size(); i++) {
				String sql = " insert into wifiinfo (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
						+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";

				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, ((Info) list.get(i)).getX_SWIFI_MGR_NO());
				preparedStatement.setString(2, ((Info) list.get(i)).getX_SWIFI_WRDOFC());
				preparedStatement.setString(3, ((Info) list.get(i)).getX_SWIFI_MAIN_NM());
				preparedStatement.setString(4, ((Info) list.get(i)).getX_SWIFI_ADRES1());
				preparedStatement.setString(5, ((Info) list.get(i)).getX_SWIFI_ADRES2());
				preparedStatement.setString(6, ((Info) list.get(i)).getX_SWIFI_INSTL_FLOOR());
				preparedStatement.setString(7, ((Info) list.get(i)).getX_SWIFI_INSTL_TY());
				preparedStatement.setString(8, ((Info) list.get(i)).getX_SWIFI_INSTL_MBY());
				preparedStatement.setString(9, ((Info) list.get(i)).getX_SWIFI_SVC_SE());
				preparedStatement.setString(10, ((Info) list.get(i)).getX_SWIFI_CMCWR());
				preparedStatement.setString(11, ((Info) list.get(i)).getX_SWIFI_CNSTC_YEAR());
				preparedStatement.setString(12, ((Info) list.get(i)).getX_SWIFI_INOUT_DOOR());
				preparedStatement.setString(13, ((Info) list.get(i)).getX_SWIFI_REMARS3());
				preparedStatement.setDouble(14, ((Info) list.get(i)).getLAT());
				;
				preparedStatement.setDouble(15, ((Info) list.get(i)).getLNT());
				preparedStatement.setString(16, ((Info) list.get(i)).getWORK_DTTM());

				preparedStatement.executeUpdate();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}

	}

	public List<Info> dbSelect(double lnt, double lat) {

		String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "pass";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		List<Info> wifilist = new ArrayList<>();

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "SELECT " + "ROUND( " + "(6371 * ACOS( "
					+ "COS(RADIANS(?)) * COS(RADIANS(wi.LAT)) * COS(RADIANS(wi.LNT) - RADIANS(?)) + "
					+ "SIN(RADIANS(?)) * SIN(RADIANS(wi.LAT)) " + ")), " + "4 " + ") AS distance, " + "wi.* "
					+ "FROM wifiinfo wi " + "ORDER BY distance " + "LIMIT 0, 20 ";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setDouble(1, lnt);
			preparedStatement.setDouble(2, lat);
			preparedStatement.setDouble(3, lnt);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Info info = new Info();
				info.setDistan(rs.getDouble("distance"));
				info.setX_SWIFI_MGR_NO(rs.getString("wi.X_SWIFI_MGR_NO"));
				info.setX_SWIFI_WRDOFC(rs.getString("wi.X_SWIFI_WRDOFC"));
				info.setX_SWIFI_MAIN_NM(rs.getString("wi.X_SWIFI_MAIN_NM"));
				info.setX_SWIFI_ADRES1(rs.getString("wi.X_SWIFI_ADRES1"));
				info.setX_SWIFI_ADRES2(rs.getString("wi.X_SWIFI_ADRES2"));
				info.setX_SWIFI_INSTL_FLOOR(rs.getString("wi.X_SWIFI_INSTL_FLOOR"));
				info.setX_SWIFI_INSTL_TY(rs.getString("wi.X_SWIFI_INSTL_TY"));
				info.setX_SWIFI_INSTL_MBY(rs.getString("wi.X_SWIFI_INSTL_MBY"));
				info.setX_SWIFI_SVC_SE(rs.getString("wi.X_SWIFI_SVC_SE"));
				info.setX_SWIFI_CMCWR(rs.getString("wi.X_SWIFI_CMCWR"));
				info.setX_SWIFI_CNSTC_YEAR(rs.getString("wi.X_SWIFI_CNSTC_YEAR"));
				info.setX_SWIFI_INOUT_DOOR(rs.getString("wi.X_SWIFI_INOUT_DOOR"));
				info.setX_SWIFI_REMARS3(rs.getString("wi.X_SWIFI_REMARS3"));
				info.setLAT(rs.getDouble("wi.LAT"));
				info.setLNT(rs.getDouble("wi.LNT"));
				info.setWORK_DTTM(rs.getString("wi.WORK_DTTM"));

				wifilist.add(info);

				System.out.println(info.getDistan() + "," + info.getX_SWIFI_MGR_NO() + "," + info.getX_SWIFI_WRDOFC()
						+ "," + info.getX_SWIFI_MAIN_NM() + "," + info.getX_SWIFI_ADRES1() + ","
						+ info.getX_SWIFI_ADRES2() + "," + info.getX_SWIFI_INSTL_FLOOR() + ","
						+ info.getX_SWIFI_INSTL_TY() + "," + info.getX_SWIFI_INSTL_MBY() + ","
						+ info.getX_SWIFI_SVC_SE() + "," + info.getX_SWIFI_CMCWR() + "," + info.getX_SWIFI_CNSTC_YEAR()
						+ "," + info.getX_SWIFI_INOUT_DOOR() + "," + info.getX_SWIFI_REMARS3() + "," + info.getLAT()
						+ "," + info.getLNT() + "," + info.getWORK_DTTM());
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		return wifilist;
	}

	
	//위치 관련
	public void locationList(double lnt, double lat) {

		String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "pass";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = " insert into location_history (X좌표, Y좌표, 조회일자) " + " values (?, ?, ?); ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, lnt);
			preparedStatement.setDouble(2, lat);
			preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			int affected = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}

	}

	public List<locgetset> locationSelect() {

		String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "pass";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		List<locgetset> locationlist = new ArrayList<>();

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "SELECT * " 
					+ "from location_history ";
					
			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				locgetset loc = new locgetset();
				loc.setID(rs.getInt("ID"));
				loc.setXloc(rs.getDouble("X좌표"));
				loc.setYloc(rs.getDouble("Y좌표"));
				loc.setSearchdate(rs.getTimestamp("조회일자"));				

				locationlist.add(loc);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		return locationlist;
	}

	public void locationDelete(int ID) {


        String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "pass";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " delete from location_history " +
                    " where id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            

            int affected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

    }

	
	//와이파이 상세정보
	public Info wifiDetail(String num, double dis) {

		String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "pass";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		Info info = new Info();

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "SELECT * " + "from wifiinfo wi " + "where X_SWIFI_MGR_NO = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, num);


			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				info.setDistan(dis);
				info.setX_SWIFI_MGR_NO(rs.getString("wi.X_SWIFI_MGR_NO"));
				info.setX_SWIFI_WRDOFC(rs.getString("wi.X_SWIFI_WRDOFC"));
				info.setX_SWIFI_MAIN_NM(rs.getString("wi.X_SWIFI_MAIN_NM"));
				info.setX_SWIFI_ADRES1(rs.getString("wi.X_SWIFI_ADRES1"));
				info.setX_SWIFI_ADRES2(rs.getString("wi.X_SWIFI_ADRES2"));
				info.setX_SWIFI_INSTL_FLOOR(rs.getString("wi.X_SWIFI_INSTL_FLOOR"));
				info.setX_SWIFI_INSTL_TY(rs.getString("wi.X_SWIFI_INSTL_TY"));
				info.setX_SWIFI_INSTL_MBY(rs.getString("wi.X_SWIFI_INSTL_MBY"));
				info.setX_SWIFI_SVC_SE(rs.getString("wi.X_SWIFI_SVC_SE"));
				info.setX_SWIFI_CMCWR(rs.getString("wi.X_SWIFI_CMCWR"));
				info.setX_SWIFI_CNSTC_YEAR(rs.getString("wi.X_SWIFI_CNSTC_YEAR"));
				info.setX_SWIFI_INOUT_DOOR(rs.getString("wi.X_SWIFI_INOUT_DOOR"));
				info.setX_SWIFI_REMARS3(rs.getString("wi.X_SWIFI_REMARS3"));
				info.setLAT(rs.getDouble("wi.LAT"));
				info.setLNT(rs.getDouble("wi.LNT"));
				info.setWORK_DTTM(rs.getString("wi.WORK_DTTM"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		return info;
	}

	
	//북마크 관련
	public void bookmarkInsert(String name, int turn) {

        String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "pass";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " insert into bookmark_group(NAME, TURN, RESISTER) " +
                    " values (?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, name);
			preparedStatement.setInt(2, turn);
			preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
    

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
	
	public List<bookgetset> bookmarkSelect() {

		String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "pass";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		List<bookgetset> bookmarklist = new ArrayList<>();

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "SELECT * " 
					+ "from bookmark_group ";
					
			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bookgetset bok = new bookgetset();
				bok.setID(rs.getInt("ID"));
				bok.setName(rs.getString("NAME"));
				bok.setTurn(rs.getInt("TURN"));
				bok.setResisterdate(rs.getTimestamp("RESISTER"));
				bok.setModifydate(rs.getTimestamp("MODIFY"));

				bookmarklist.add(bok);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		return bookmarklist;
	}

	public void bookmarkDelete(int ID) {


        String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "pass";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " delete from bookmark_group " +
                    " where ID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

	public void bookmarkUpdate(String newname, int newnum, int ID) {

	        String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
	        String dbUserId = "testuser1";
	        String dbPassword = "pass";

	        try {
	            Class.forName("org.mariadb.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException(e);
	        }
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet rs = null;

	        try {
	            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


	            String sql = " update bookmark_group " +
	                    " set " +
	            		" MODIFY = ? " +
	                    " ,name = ? " + ",turn = ? " +
	                    " where ID = ? ";
	                  
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
	            preparedStatement.setString(2, newname);
	            preparedStatement.setInt(3, newnum);
	            preparedStatement.setInt(4, ID);	       

	            preparedStatement.executeUpdate();


	        } catch (SQLException e) {
	            throw new RuntimeException(e);

	        } finally {

	            try {
	                if (rs != null && !rs.isClosed()) {
	                    rs.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }

	            try {
	                if (preparedStatement != null && !preparedStatement.isClosed()) {
	                    preparedStatement.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }

	            try {
	                if (connection != null && !connection.isClosed()) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }


	        }

	    }

	public List<bookgetset> bookmarkNameSelect() {

		String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "pass";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		List<bookgetset> bookmarknamelist = new ArrayList<>();

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "SELECT * " 
					+ "from bookmark_group ";
					
			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bookgetset bok = new bookgetset();
				bok.setName(rs.getString("NAME"));
				
				bookmarknamelist.add(bok);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		return bookmarknamelist;
	}

	public void bookmarkadd(String bookname, String wifiname) {

        String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "pass";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " insert into bookmark_add(bookname, wifiname, resi) " +
                    " values (?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, bookname);
			preparedStatement.setString(2, wifiname);
			preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
    

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

	public List<booklistgetset> booklistSelect() {

		String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "pass";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		List<booklistgetset> booklist = new ArrayList<>();

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = "SELECT * " 
					+ "from bookmark_add ";
					
			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				booklistgetset book = new booklistgetset();
				book.setID(rs.getInt("ID"));
				book.setBookname(rs.getString("bookname"));
				book.setWifiname(rs.getString("wifiname"));
				book.setResi(rs.getTimestamp("resi"));

				booklist.add(book);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		return booklist;
	}

	public void booklistDelete(int ID) {


        String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "pass";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " delete from bookmark_add " +
                    " where ID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
