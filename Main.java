import ithakimodem.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.lang.*;
public class Main {

	public static void main(String[] args) {
		Modem modem = new Modem();
		modem.setTimeout(2000);
		modem.setSpeed(1000);
		modem.open("ithaki");
		OutputStream op = modem.getOutputStream();
		InputStream ip = modem.getInputStream();
		int k;
		String erc = "E4516\r";
		long time = 0;
		long[][] g = new long[200000][200000];
		
			for(;;) {
				try {
	//				op.write(erc.getBytes());
	//				ip.read();
					long time_now = System.currentTimeMillis();
					modem.write(erc.getBytes());
					k = modem.read();
					if(k==-1) break;
					//System.out.print((char)k);
					
					
					long time_later = System.currentTimeMillis();
					long package_time = time_later-time_now;
					System.out.println(package_time);
					time+=package_time;
					//System.out.println(time);
					for(int i=0; i<240000; i+=package_time) {
						for(int j=0; j<240000; j+=package_time) {
							g[i][j] = {{time}, {package_time}};
						}
					}
					if(time>=240000) {
						
						break;
					}
				} catch(Exception x) {
					break;
				}
			}
			
		
		//modem.write((int)'E');
		
		
		
		
		modem.close();
	}

}
