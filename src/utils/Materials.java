package utils;

import javax.media.j3d.Material;

public class Materials extends Material {
	public final static int BRASS = 0;
	public final static int BRONZE = 1;
	public final static int MYMATERIAL_1 = 2;
	public final static int MYMATERIAL_2 = 3;
	public final static int GOLD = 4;
	public final static int CHROME = 5;
	public final static int POLISHED_GOLD = 6 ;
	public final static int COPPER = 7 ;
	public Materials(int type) {
		switch (type) {
		case BRASS:
			this.setAmbientColor(0.329412f, 0.223529f, 0.027451f);
			this.setDiffuseColor(0.790392f, 0.568627f, 0.113725f);
			this.setSpecularColor(0.992157f, 0.941176f, 0.807843f);
			this.setShininess(27.8974f);
			break;
		case BRONZE:
			this.setAmbientColor(0.2125f, 0.1275f, 0.054f);
			this.setDiffuseColor(0.714f, 0.4284f, 0.18144f);
			this.setSpecularColor(0.393548f, 0.271906f, 0.166721f);
			this.setShininess(25.6f);
			break;
		case MYMATERIAL_1:
			this.setAmbientColor(0.50f, 0.33f, 0.70f);
			this.setDiffuseColor(0.078431f, 0.13431f, 0.078431f);
			this.setSpecularColor(0.33f, 0.45f, 0.63f);
			this.setShininess(96.3f);
			break;
		case MYMATERIAL_2:
			this.setAmbientColor(0.90f, 0.73f, 0.80f);
			this.setDiffuseColor(0.178431f, 0.23431f, 0.098431f);
			this.setSpecularColor(0.66f, 0.65f, 0.73f);
			this.setShininess(113.9f);
			break;
		case GOLD:
			this.setAmbientColor(0.24725f, 0.1995f, 0.0745f);
			this.setDiffuseColor(0.075164f, 0.60648f, 0.22648f);
			this.setSpecularColor(0.628281f, 0.555802f, 0.366065f);
			this.setShininess(51.2f);
			break;
		case CHROME:
			this.setAmbientColor(0.25f, 0.25f, 0.25f);
			this.setDiffuseColor(0.4f, 0.4f, 0.4f);
			this.setSpecularColor(0.774597f, 0.774597f, 0.774597f);
			this.setShininess(76.8f);
			break;
		case POLISHED_GOLD:
			this.setAmbientColor(0.24725f, 0.2245f, 0.0645f);
			this.setDiffuseColor(0.34615f, 0.3143f, 0.0903f);
			this.setSpecularColor(0.797357f, 0.723991f, 0.208006f);
			this.setShininess(83.2f);
			break;
		case COPPER:
			this.setAmbientColor(0.19125f, 0.0735f, 0.0275f);
			this.setDiffuseColor(0.7038f, 0.27048f, 0.066f);
			this.setSpecularColor(0.25677f, 0.137622f, 0.086014f);
			this.setShininess(12.8f);
			break;
		}
	}
}
