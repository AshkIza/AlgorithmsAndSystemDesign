package DesignPatterns.Proxy;

/* https://stackabuse.com/the-proxy-design-pattern-in-java/
 * Virtual Proxies 
 * wrap expensive objects and loads them on-demand. 
 * Sometimes we don't immediately need all functionalities that an object offers, 
 * especially if it is memory/time-consuming. 
 * Calling objects only when needed might increase performance quite a bit (lazy loading)
 * 
 * Real world example: https://howtodoinjava.com/design-patterns/structural/proxy-design-pattern/
 * In hibernate, we write the code to fetch entities from the database. 
 * Hibernate returns an object which a proxy 
 * (by dynamically constructed by Hibernate by extending the domain class) to the underlying entity class. 
 * The client code is able to read the data whatever it needs to read with the proxy.
 * These proxy entity classes help in 
 * 	implementing lazy loading scenarios where associated entities are fetched only when they are requested explicitly.
 *  It helps in improving performance of DAO operations.
*/

// avoid calling the expensive object/operation as late as possible
public class VirtualProxyExample {
	
	public static class Image {
		String url;
		public Image(String url) {
			this.url = url;
		}
		String getUrl() {
			return url;
		}
	}
	
	/* two costly operations:
	 * 	    1- load image from its path 
	 *  	2- displays it after loaded*/
	public interface ImageViewer {
		public void dispalyImage();
	}
	
	public static class ConcreteImgaeViwer implements ImageViewer {
		Image loadedImage;
		
		ConcreteImgaeViwer(String path) {
			loadedImage = loadImage(path);// costly
		}
		
		@Override
		public void dispalyImage() {
			System.out.print(" displaying image from path : " + loadedImage.getUrl() + "\n...\n...");
		}
		
		// costly/expensive object
		Image loadImage(String path) {
			System.out.print(" loading the image from path " + path + " ...\n");
			return new Image(path);
		}
	}
	
	public static class ProxyImgaeViwer implements ImageViewer {
		ConcreteImgaeViwer imageViewer;
		String path;
		ProxyImgaeViwer(String path){
			this.path = path;
			// delaying the costly loadImage() as latest as possible
		}
		
		@Override
		public void dispalyImage() {
			if(imageViewer == null) { // no need to load if already loaded
				imageViewer = new ConcreteImgaeViwer(path);//lazy loading once requested
			}
			imageViewer.dispalyImage();
		}
	}
	

	public static void main(String[] args) {
		System.out.println("Virtual Proxies ");
		System.out.println("wrap expensive objects and loads them on-demand.");
		System.out.print("\nnot using proxy:\n");
		ImageViewer imageViewer01 = new ConcreteImgaeViwer("path/01/");
		ImageViewer imageViewer02 = new ConcreteImgaeViwer("path/02/");
		ImageViewer imageViewer03 = new ConcreteImgaeViwer("path/03/");
		
		System.out.print("\nusing proxy:\n");
	    imageViewer01 = new ProxyImgaeViwer("path/01/");
		imageViewer02 = new ProxyImgaeViwer("path/02/");
	    imageViewer03 = new ProxyImgaeViwer("path/03/");
		imageViewer01.dispalyImage();
		imageViewer01.dispalyImage();
	}

}
