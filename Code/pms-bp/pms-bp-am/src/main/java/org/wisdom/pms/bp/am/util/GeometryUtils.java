package org.wisdom.pms.bp.am.util;

import java.math.BigDecimal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GeometryUtils
{
	/**
	 * 点和线段的最短距离
	 */
	public static double getMinDistance(double pointX, double pointY, double lineSegmentX0, double lineSegmentY0, double lineSegmentX1, double lineSegmentY1)
	{
		BigDecimal pointXvalue = BigDecimal.valueOf(pointX);
		BigDecimal pointYvalue = BigDecimal.valueOf(pointY);
		BigDecimal lineSegmentX0value = BigDecimal.valueOf(lineSegmentX0);
		BigDecimal lineSegmentY0value = BigDecimal.valueOf(lineSegmentY0);
		BigDecimal lineSegmentX1value = BigDecimal.valueOf(lineSegmentX1);
		BigDecimal lineSegmentY1value = BigDecimal.valueOf(lineSegmentY1);

		// 获得发布段的斜率
		BigDecimal slope = lineSegmentY0value.subtract(lineSegmentY1value).divide(lineSegmentX0value.subtract(lineSegmentX1value), 2, BigDecimal.ROUND_HALF_EVEN);
		// 获得发布段的y轴截距
		BigDecimal intercept = lineSegmentY0value.subtract(slope.multiply(lineSegmentX0value));
		// 获得警情和发布段交点垂足横坐标
		// 点和线段的横纵坐标公式:x=(x0+k*y0-k*b)/(k^2+1),y=(k*x0+k^2*y0+b)/(k^2+1),其中(x0,y0)表示直线外的点，k表示直线斜率，b表示直线纵轴截距
		BigDecimal footPointX = (pointXvalue.add(slope.multiply(pointYvalue)).subtract(slope.multiply(intercept))).divide(slope.multiply(slope).add(BigDecimal.valueOf(1)), 2, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal footPointY = (slope.multiply(pointXvalue).add(slope.multiply(slope).multiply(pointYvalue)).divide(slope.multiply(slope).add(BigDecimal.valueOf(1)), 2, BigDecimal.ROUND_HALF_EVEN));

		BigDecimal minDistance = null;

		if (footPointX.doubleValue() >= Math.min(lineSegmentX0value.doubleValue(), lineSegmentX1value.doubleValue()) && footPointX.doubleValue() <= Math.max(lineSegmentX0value.doubleValue(), lineSegmentX1value.doubleValue()))
		{
			minDistance = BigDecimal.valueOf(Math.sqrt((Math.pow((footPointX.doubleValue() - pointXvalue.doubleValue()), 2) + Math.pow((footPointY.doubleValue() - pointYvalue.doubleValue()), 2))));
		}
		else
		{
			BigDecimal distance1 = BigDecimal.valueOf(Math.sqrt((Math.pow((pointXvalue.doubleValue() - lineSegmentX0value.doubleValue()), 2) + Math.pow((pointYvalue.doubleValue() - lineSegmentY0value.doubleValue()), 2))));
			BigDecimal distance2 = BigDecimal.valueOf(Math.sqrt((Math.pow((pointXvalue.doubleValue() - lineSegmentX1value.doubleValue()), 2) + Math.pow((pointYvalue.doubleValue() - lineSegmentY1value.doubleValue()), 2))));
			minDistance = BigDecimal.valueOf(Math.min(distance1.doubleValue(), distance2.doubleValue()));
		}

		return minDistance.doubleValue();
	}

	/**
	 * 经纬度转高斯投影直角坐标系（B：代表纬度，L：代表经度）
	 */
	public static double[] transformBlToGauss(double longitude, double latitude)
	{
		// π/180，1度所对应的弧度
		double iPI = 0.0174532925199433;
		// 地带宽度（6度带）
		int zoneWide = 6;
		// 地带编号（编号从1开始，1、2、3、4.。。）四舍五入取整
		int zoneNo = (int) Math.round((longitude + 3) / zoneWide);
		// 中央经线的经度
		double longitude0 = zoneNo * zoneWide - zoneWide / 2;
		// 中央经线的弧度
		longitude0 = longitude0 * iPI;
		// 目标经线的弧度
		double longitude1 = longitude * iPI;
		// 目标纬线的弧度
		double latitude1 = latitude * iPI;
		// 长半轴（地球是椭圆的）
		double longAxis = 6378245.0;
		// 扁率（Flattening or Compression）（赤道半径（长半轴a）和极半径（短半轴b）的差与赤道半径的比值）
		double f = 1.0 / 298.25;
		// 第一偏心率（First Eccentricity）
		double e1 = 2 * f - f * f;
		// 第二偏心率（Second Eccentricity）
		double e2 = e1 * (1.0 - e1);
		// 卯酉圈曲率半径（地球椭球体表面上某点法截弧曲率半径中最大的曲率半径）
		double N = longAxis / Math.sqrt(1.0 - e1 * Math.sin(latitude1) * Math.sin(latitude1));
		// 子午圈曲率半径（地球椭球体表面上某点法截弧曲率半径中最小的曲率半径）
		double M = longAxis * ((1 - e1 / 4 - 3 * e1 * e1 / 64 - 5 * e1 * e1 * e1 / 256) * latitude1 - (3 * e1 / 8 + 3 * e1 * e1 / 32 + 45 * e1 * e1 * e1 / 1024) * Math.sin(2 * latitude1) + (15 * e1 * e1 / 256 + 45 * e1 * e1 * e1 / 1024) * Math.sin(4 * latitude1)
				- (35 * e1 * e1 * e1 / 3072) * Math.sin(6 * latitude1));

		double T = Math.tan(latitude1) * Math.tan(latitude1);
		double C = e2 * Math.cos(latitude1) * Math.cos(latitude1);
		// 同一纬度上的经差
		double A = (longitude1 - longitude0) * Math.cos(latitude1);
		double xval = N * (A + (1 - T + C) * A * A * A / 6 + (5 - 18 * T + T * T + 72 * C - 58 * e2) * A * A * A * A * A / 120);
		double yval = M + N * Math.tan(latitude1) * (A * A / 2 + (5 - T + 9 * C + 4 * C * C) * A * A * A * A / 24 + (61 - 58 * T + T * T + 600 * C - 330 * e2) * A * A * A * A * A * A / 720);
		
		double X0 = 1000000L * zoneNo + 500000L;
		double Y0 = 0;

		xval = xval + X0;
		yval = yval + Y0;

		double[] coordinate = new double[2];

		coordinate[0] = xval;
		coordinate[1] = yval;

		return coordinate;
	}
	
	/**
	 * 调用百度地图接口实现案发地址到经纬度的转换
	 */
	public static Object[] getCoordinateByAddr(String addr) throws IOException {
		String lng = null;// 经度
		String lat = null;// 纬度
		String address = null;
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String key = "77qyiChpsxKI8saofafgu6GQpwladYhP";
		String url = String.format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, key);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader insr = null;
		BufferedReader br = null;
		try {
			httpsConn = (URLConnection) myURL.openConnection();// 使用代理
            // 建立实际的连接  
			httpsConn.connect();
			if (httpsConn != null) {
				insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				br = new BufferedReader(insr);
				String data = null;
				int count = 1;
				while ((data = br.readLine()) != null) {
					if (count == 5) {
						lng = (String) data.subSequence(data.indexOf(":") + 1, data.indexOf(","));// 经度
						count++;
					} else if (count == 6) {
						lat = data.substring(data.indexOf(":") + 1);// 纬度
						count++;
					} else {
						count++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (insr != null) {
				insr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		return new Object[] { lng, lat };
	}
	public static void main(String[] args)
	{
		try
		{
			System.out.println("经度" + getCoordinateByAddr("山西省长治市")[0]);
			System.out.println("纬度" + getCoordinateByAddr("山西省长治市")[1]);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
