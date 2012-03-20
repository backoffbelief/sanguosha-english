package com.sdsoft.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import craky.util.SwingResourceManager;

public class ImageUtil {

	/**
	 * cache to store image
	 */
	private static Map<String, Image> imageCache = new HashMap<String, Image>();
	private static Map<String, ImageIcon> iconCache = new HashMap<String, ImageIcon>();

	/**
	 * Get image icon with specific name
	 * 
	 * @param name
	 *            of image icon
	 * @return image icon
	 */
	public static ImageIcon getIcon(final String name) {
		if (iconCache.containsKey(name)) {
			return iconCache.get(name);

		} else {
			final ImageIcon icon = SwingResourceManager.getIcon(name);

			if (icon == null) {
				throw new RuntimeException("Icon {name:" + name + "} does not exist");
			} else {
				iconCache.put(name, icon);
			}
			return icon;
		}
	}

	/**
	 * Get image with specific name
	 * 
	 * @param name
	 *            of image
	 * @return image
	 */
	public static Image getImage(final String name) {
		if (imageCache.containsKey(name)) {
			return imageCache.get(name);

		} else {
			final Image image = new ImageIcon(name).getImage();

			if (image == null) {
				throw new RuntimeException("Image {name:" + name + "} does not exist");
			} else {
				imageCache.put(name, image);
			}
			return image;
		}
	}
}
