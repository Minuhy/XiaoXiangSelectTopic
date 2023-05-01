package minuhy.xiaoxiang.lectopic.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * ����һ��ͼƬ��֤��
 * 
 * @author y17mm
 * @time 2023-2-13 10:23:06
 * @version 1.0
 */
public class CaptchaUtil {
	public class CaptchaInfo{
		public String formula;
		public String result;
		public BufferedImage image;
	}
	
	static CaptchaUtil captchaUtil;
	
	//���������
	private static final Random random = new Random();

	// ����
	private static Font font;
	
	/**
	 * ��ȡ�����ɫ
	 * @return һ���������ɫ
	 */
	public Color getColor() {
	
	    int red = random.nextInt(200);
	    int green = random.nextInt(200);
	    int blue = random.nextInt(200);
	
	    return new Color(red, green, blue);
	}

	/**
	 * ������֤��
	 * @return ��֤������
	 */
	public CaptchaInfo GgenerateCaptcha(int width,int height,int line,int fontSize,String fontName) {
	    
		// ��ʼ������
		if(font==null 
	    		|| font.getSize() != fontSize
	    		|| !(font.getFontName().equals(fontName))
	    		) {
	    	font = new Font(fontName, Font.PLAIN, fontSize);
	    }
	    
		// ��ȡ��֤����
		CaptchaInfo info = new CaptchaInfo();
		String formula = "0/7="; // ��ʽ
	    String result = "0"; // ���
	    {
	        int a, b;
	
	        a = random.nextInt(10);
	        b = random.nextInt(10);
	
	        switch (random.nextInt(3)) {
	            case 0:
	                // �ӷ�
	                formula = a + "��" + b;
	                result = String.valueOf(a + b);
	                break;
	            case 1:
	                // ����
	                if (a < b) {
	                    int t = a;
	                    a = b;
	                    b = t;
	                }
	                formula = a + "��" + b;
	                result = String.valueOf(a - b);
	                break;
	            case 2:
	                formula = a + "��" + b;
	                result = String.valueOf(a * b);
	                break;
	        }
	    }
	
	    // ��֤����� ���ؽ����
	    info.result = result;
	    info.formula = formula;
	
	
	    // ����
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    // ����
	    Graphics pen = image.getGraphics();
	    // ����
	    pen.setFont(font);
	
	    // ���x
	    int xRandom = (width - (fontSize * formula.length())) / formula.length();
	    if (xRandom < 1) {
	        xRandom = 1;
	    }
	
	    // ���y
	    int yRandom = height - fontSize;
	    if (yRandom < 1) {
	        yRandom = 1;
	    }
	
	    int xOffset = width / formula.length();
	
	    // ����ͼƬ
	    for (int i = 0; i < formula.length(); i++) {
	        // ��ɫ
	        pen.setColor(getColor());
	
	        // ����
	        pen.drawString(
	                String.valueOf(formula.charAt(i)),
	                i * xOffset + random.nextInt(xRandom),
	                fontSize + random.nextInt(yRandom)
	        );
	    }
	
	    for (int i = 0; i < line; i++) {
	        // ��ɫ
	        pen.setColor(getColor());
	        // ����
	        pen.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
	    }
	
	    // ���ͼƬ��
	    info.image = image;
	    
	    return info;
	}
}
