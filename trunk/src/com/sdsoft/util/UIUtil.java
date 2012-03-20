package com.sdsoft.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.FocusListener;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class UIUtil {

	public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();
	public static final NumberFormat FLOAT_FORMAT = NumberFormat.getNumberInstance();
	public static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();
	public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
	public static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static final DateFormat DATETIME_MS_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss SSSSS");
	public static final DateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm:ss a");
	static {
		FLOAT_FORMAT.setMaximumFractionDigits(3);
	}

	private static NumberFormat floatFormat = NumberFormat.getNumberInstance();

	public static String formatFloat(final Number num, final int fractionDigits) {
		floatFormat.setMaximumFractionDigits(fractionDigits);
		return floatFormat.format(num);
	}

	public static String formatPercent(final Number num, final int fractionDigits) {
		floatFormat.setMaximumFractionDigits(fractionDigits);
		return floatFormat.format(num.doubleValue() * 100) + "%";
	}

	public static String formatCurrency(final Number num) {
		return CURRENCY_FORMAT.format(num);
	}

	public static String formatDate(final Date date) {
		return DATE_FORMAT.format(date);
	}

	public static String formatDateTime(final Date date) {
		return DATETIME_FORMAT.format(date);
	}

	public static void justifyComponents(final List<? extends Component> components) {
		int max = 0;
		for (final Component component : components) {
			if (component.getPreferredSize().width > max) {
				max = component.getPreferredSize().width;
			}
		}
		for (final Component component : components) {
			final Dimension dimension = new Dimension(max, component.getPreferredSize().height);
			component.setPreferredSize(dimension);
			component.setMaximumSize(dimension);
		}
	}

	public final static Component addFocusListenerToContainer(final Container container, final FocusListener focusListener) {
		if (container.isFocusable()) {
			container.addFocusListener(focusListener);
			return container;
		}
		final Component[] cs = container.getComponents();
		for (int i = 0; i < cs.length; i++) {
			if (cs[i].isFocusable()) {
				cs[i].addFocusListener(focusListener);
				return cs[i];
			} else if (cs[i] instanceof Container) {
				final Component c = addFocusListenerToContainer((Container) cs[i], focusListener);
				if (c != null) {
					return c;
				}
			}
		}
		return null;
	}

	public static void justifyComponents(final JComponent... components) {
		int max = 0;
		for (final JComponent component : components) {
			if (component.getPreferredSize().width > max) {
				max = component.getPreferredSize().width;
			}
		}
		for (final JComponent component : components) {
			final Dimension dimension = new Dimension(max, component.getPreferredSize().height);
			component.setPreferredSize(dimension);
			component.setMaximumSize(dimension);
		}
	}

	private static final JLabel LABEL = new JLabel("");
	private static final String FONT_NAME = LABEL.getFont().getName();
	private static final FontMetrics FONT_METRICS = LABEL.getFontMetrics(LABEL.getFont());

	public static String getTableColumnHeaderHtmlString(final String str) {
		return "<html><font face=\"" + FONT_NAME + "\" size=\"-2\">" + str + "</font></html>";
	}

	public static String getHtmlString(final String str) {
		return "<html><font face=\"" + FONT_NAME + "\">" + str + "</font></html>";
	}

	public static String ignoreHTMLTags(final String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		}
		final StringBuffer buf = new StringBuffer();
		int tagStartIndex = str.indexOf('<');
		if (tagStartIndex > 0) {
			buf.append(str.substring(0, tagStartIndex));
		}
		int tagEndIndex;
		int lastCopiedIndex = -1;
		while (tagStartIndex >= 0 && tagStartIndex < str.length()) {
			tagEndIndex = str.indexOf('>', tagStartIndex + 1);
			if (tagEndIndex < 0) {
				break;
			}
			lastCopiedIndex = tagEndIndex;
			buf.append(' ');
			tagStartIndex = str.indexOf('<', tagEndIndex + 1);
			if ((tagStartIndex - tagEndIndex) > 1) {
				buf.append(str.substring(tagEndIndex + 1, tagStartIndex));
			}
		}
		if ((lastCopiedIndex + 1) < str.length()) {
			buf.append(str.substring(lastCopiedIndex + 1));
		}
		return buf.toString().trim();
	}

	public static boolean isParent(Component component, final Container container) {
		while (component.getParent() != null && component.getParent() != container) {
			component = component.getParent();
		}
		return component.getParent() == container;
	}

	public static void scrollInvisibleComponentToCenter(final JComponent component) {
		JComponent parent = component;
		while (parent.getParent() != null && parent instanceof JComponent && !(parent.getParent() instanceof JViewport)) {
			parent = (JComponent) parent.getParent();
		}
		final Rectangle visibleRectangle = parent.getVisibleRect();

		final Rectangle rectangleToScroll = new Rectangle(visibleRectangle);
		Rectangle componentRectangle = new Rectangle(0, 0, component.getWidth(), component.getHeight());
		if (parent != component) {
			componentRectangle = SwingUtilities.convertRectangle(component, componentRectangle, parent);
		}

		// if(visibleRectangle.contains(componentRectangle))
		// return;

		if (visibleRectangle.width == 0 || visibleRectangle.height == 0) {
			rectangleToScroll.x = Math.max(0, componentRectangle.x - (850 - componentRectangle.width) / 2);
			rectangleToScroll.y = Math.max(0, componentRectangle.y - (400 - componentRectangle.height) / 2);
		} else {
			rectangleToScroll.x = Math.max(0, componentRectangle.x - (visibleRectangle.width - componentRectangle.width) / 2);
			rectangleToScroll.y = Math.max(0, componentRectangle.y - (visibleRectangle.height - componentRectangle.height) / 2);
		}
		parent.scrollRectToVisible(rectangleToScroll);
	}

	public static void scrollComponentToCenter(final JComponent component) {
		scrollToCenter(component.getBounds(), component.getParent());
	}

	public static void scrollToCenter(final Rectangle rectangle, final Component rectangleParent) {
		Component parent = rectangleParent;
		while (parent.getParent() != null && parent instanceof JComponent && !(parent.getParent() instanceof JViewport)) {
			parent = parent.getParent();
		}
		if (parent == null || !(parent instanceof JComponent)) {
			return;
		}
		final Rectangle visibleRectangle = ((JComponent) parent).getVisibleRect();
		final Rectangle rectangleToScroll = new Rectangle(visibleRectangle);
		final Rectangle componentRectangle = SwingUtilities.convertRectangle(rectangleParent, rectangle, parent);
		rectangleToScroll.x = Math.max(0, componentRectangle.x - (visibleRectangle.width - componentRectangle.width) / 2);
		rectangleToScroll.y = Math.max(0, componentRectangle.y - (visibleRectangle.height - componentRectangle.height) / 2);
		((JComponent) parent).scrollRectToVisible(rectangleToScroll);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getParentComponent(Component component, final Class<T> componentClass) {
		while (component != null && !(componentClass.isAssignableFrom(component.getClass()))) {
			component = component.getParent();
		}
		if (component != null && componentClass.isAssignableFrom(component.getClass())) {
			return (T) component;
		} else {
			return null;
		}
	}

	// public static Thread startCommandOnSeparateThread(final Command command,
	// final String title){
	// Thread thread = new Thread(){
	// @Override
	// public void run(){
	// try {
	// CommandController.getInstance().execute(command, title);
	// } catch (Exception e){
	// ErrorHandler.handleException(e);
	// }
	// }
	// };
	// thread.start();
	// return thread;
	// }

	public static final Color[] COLOR_LIST = new Color[] {
			new Color(100, 255, 100),
			new Color(100, 100, 255),
			new Color(100, 255, 255),
			new Color(120, 220, 220),
			new Color(255, 255, 120),
			new Color(200, 200, 255) };
	public static final Color OVER_FILLED_COLOR = new Color(255, 100, 100);
	public static final Color UNAVAILABLE_COLOR = new Color(200, 200, 200);

	// ##sn20090430-->
	public static JPanel createNewPanel(final Component... components) {
		final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		for (final Component c : components) {
			panel.add(c);
		}
		return panel;
	}

	// ##sn20090430<--

	// <rt
	public static JPanel createNewPanel(final LayoutManager layoutManager, final Component... components) {
		final JPanel panel = new JPanel(layoutManager);
		for (final Component c : components) {
			panel.add(c);
		}
		return panel;
	}

	// rt>

	public static String parseToHTML(final String text) {
		String htmlText = new String(text);
		// Replace HTML special characters with codes
		htmlText = htmlText.replace("<", "&lt;");
		htmlText = htmlText.replace(">", "&gt;");
		htmlText = htmlText.replace("\n", "<br>");

		return htmlText;
	}

	public static String getHtmlStringWithPreferredLines(final String str, final int preferredLines) {
		return getHtmlStringWithPreferredLines(str, preferredLines, "", "");
	}

	public static String getHtmlStringWithPreferredLines(final String str, final int preferredLines, final String startTag, final String endTag) {

		if (preferredLines <= 1) {
			return "<html><font face=\"" + FONT_NAME + "\" size=\"-2\">" + startTag + str + endTag + "</font></html>";
		}

		final StringTokenizer st = new StringTokenizer(str);
		final StringBuffer sb = new StringBuffer("<html><font face=\"" + FONT_NAME + "\" size=\"-2\">" + startTag);
		int width = 0;

		if (st.countTokens() <= preferredLines) {
			while (st.hasMoreElements()) {
				final String word = (String) st.nextElement();
				sb.append(word + "<br>");
			}
			sb.delete(sb.length() - 4, sb.length());
			sb.append(endTag + "</font></html>");
			return sb.toString();
		}

		else {
			final int preferredWidth = FONT_METRICS.stringWidth(str) / preferredLines;
			int line = 1;

			while (st.hasMoreElements()) {
				final String word = (String) st.nextElement();

				if (width == 0) {
					sb.append(word);
					width += FONT_METRICS.stringWidth(word);
				} else if (width + FONT_METRICS.stringWidth(" " + word) > preferredWidth && line < preferredLines) {
					sb.append(("<br>" + word));
					width = FONT_METRICS.stringWidth(word);
					line++;
				} else {
					sb.append((" " + word));
					width += FONT_METRICS.stringWidth(" " + word);
				}
			}
			sb.append("</font></html>");
			return sb.toString();
		}
	}

	@Deprecated
	/**
	 * Use JDialog.setLocationRelativeTo instead. Otherwise does not work correctly with multiple displays.
	 * @param c
	 */
	public static void centerLocation(final Component c) {
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final int x = (toolkit.getScreenSize().width - c.getWidth()) / 2;
		final int y = (toolkit.getScreenSize().height - c.getHeight() - 32) / 2;
		c.setLocation(x, y);
	}

	/**
	 * @deprecated - use SwingUtilities.getWindowAncestor instead
	 * @param parentComponent
	 * @return
	 * @throws HeadlessException
	 */
	public static Window getWindowForComponent(final Component parentComponent) throws HeadlessException {
		return SwingUtilities.getWindowAncestor(parentComponent);
		// if (parentComponent == null)
		// return JOptionPane.getRootFrame();
		// if (parentComponent instanceof Frame || parentComponent instanceof
		// Dialog)
		// return (Window)parentComponent;
		// return getWindowForComponent(parentComponent.getParent());
	}

	public static Set<Object> storeExpandedNodes(final JTree tree) {
		final HashSet<Object> expandedObjects = new HashSet<Object>();
		for (int i = 0, len = tree.getRowCount(); i < len; i++) {
			if (tree.isExpanded(i)) {
				final TreePath treePath = tree.getPathForRow(i);
				if (treePath != null) {
					expandedObjects.add(((DefaultMutableTreeNode) treePath.getLastPathComponent()).getUserObject());
				}
			}
		}
		return expandedObjects;
	}

	public static void restoreExpandedNodes(final JTree tree, final Set<Object> expandedObjects, final TreeNode startNode) {
		for (int i = 0, len = startNode.getChildCount(); i < len; i++) {
			final DefaultMutableTreeNode child = (DefaultMutableTreeNode) startNode.getChildAt(i);
			final Object obj = child.getUserObject();
			if (expandedObjects.contains(obj)) {
				tree.expandPath(new TreePath(((DefaultTreeModel) tree.getModel()).getPathToRoot(child)));
				restoreExpandedNodes(tree, expandedObjects, child);
			}
		}
	}

	private static GeneralPath axePath = new GeneralPath();

	public static void drawArrow(final Graphics2D g2d, final float x0, final float y0, final float x1, final float y1, final float height, final float width) {
		final float len = (float) Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
		final float x11 = x1 - height * (x1 - x0) / len;
		final float y11 = y1 - height * (y1 - y0) / len;

		final float x111 = x11 - width * (y1 - y0) / len;
		final float y111 = y11 + width * (x1 - x0) / len;
		final float x112 = x11 + width * (y1 - y0) / len;
		final float y112 = y11 - width * (x1 - x0) / len;

		final float x1c = x11 - width * .7f * (y1 - y0) / len;
		final float y1c = y11 + width * .5f * (x1 - x0) / len;
		final float x2c = x11 + width * .7f * (y1 - y0) / len;
		final float y2c = y11 - width * .5f * (x1 - x0) / len;

		axePath.reset();
		axePath.moveTo(x1, y1);
		axePath.quadTo(x1c, y1c, x111, y111);
		axePath.moveTo(x1, y1);
		axePath.quadTo(x2c, y2c, x112, y112);

		g2d.draw(axePath);
	}

	// david
	public static JPanel createPanel(final Component[][] components) {
		final JPanel panel2 = new JPanel();
		final GroupLayout layout = new GroupLayout(panel2);
		panel2.setLayout(layout);

		// Turn on automatically adding gaps between components
		layout.setAutoCreateGaps(true);

		// Turn on automatically creating gaps between components that touch
		// the edge of the container and the container.
		layout.setAutoCreateContainerGaps(true);

		// Create a sequential group for the horizontal axis.
		final GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		int maxColumnNum = 0;

		for (int row = 0; row < components.length; row++) {

			final GroupLayout.ParallelGroup group = layout.createParallelGroup(Alignment.BASELINE);
			GroupLayout.ParallelGroup groupComponent = group;
			maxColumnNum = Math.max(maxColumnNum, components[row].length);
			for (int col = 0; col < components[row].length; col++) {
				if (components[row][col] == null) {
					components[row][col] = new JLabel("");
				}
				final Component c = components[row][col];
				groupComponent = groupComponent.addComponent(c);
			}
			hGroup.addGroup(group);
		}
		layout.setVerticalGroup(hGroup);

		final GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		for (int col = 0; col < maxColumnNum; col++) {
			final GroupLayout.Group group = layout.createParallelGroup();
			GroupLayout.Group groupComponent = group;
			for (int row = 0; row < components.length; row++) {
				if (col < components[row].length) {
					groupComponent = groupComponent.addComponent(components[row][col]);
				}
			}
			vGroup.addGroup(group);
		}
		layout.setHorizontalGroup(vGroup);
		return panel2;
	}

	@SuppressWarnings("serial")
	public static JPanel createPanelFromImage(final String path) {

		try {
			final BufferedImage image = ImageIO.read(new File(path));

			final JPanel panel = new JPanel() {
				@Override
				public void paintComponent(final Graphics g) {
					g.drawImage(image, 0, 0, null);
				}
			};

			panel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
			return panel;

		} catch (final IOException e) {
			e.printStackTrace();
		}

		return new JPanel();
	}

	public static final String HTML_HEADER = "<html><header><title>Report</title><style type=\"text/css\"> "
			+ "* {font-family: Verdana, sans-serif; font-size: 8pt;}	" + " body { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; }	"
			+ " table {border-collapse: collapse;empty-cells: show;}	" + " td { width: 100px;border: 1px solid #444444; padding: 2px 5px 2px 5px; } "
			+ " th { width: 100px;	  border: 1px solid #444444; padding: 2px 2px 2px 2px; }"
			+ " h1 { font: 16px Arial, Verdana, Helvetica, sans-serif; font-weight: bold; color: #333333; margin-top:16px; margin-bottom:16px; }"
			+ " </style></header><body>";

	// <--

	// /**
	// * Will check if component is a read only component by calling
	// *
	// <i>UserLoginComponent.getUserLoginComponentInstance().isReadOnly(o.getClass().getName())</i>
	// *
	// * @param o component to be checked
	// * @return <b>true</b> if component is read only and not null,
	// <b>false</b> otherwise
	// */
	// public static boolean isComponentReadOnly(final Object o) {
	// return o != null &&
	// UserLoginComponent.getUserLoginComponentInstance().isReadOnly(o.getClass().getName());
	// }

	public static void createArrow(final GeneralPath path, final float x0, final float y0, final float x1, final float y1, final float height, final float width) {
		final float len = (float) Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
		final float x11 = x1 - height * (x1 - x0) / len;
		final float y11 = y1 - height * (y1 - y0) / len;

		final float x111 = x11 - width * (y1 - y0) / len;
		final float y111 = y11 + width * (x1 - x0) / len;
		final float x112 = x11 + width * (y1 - y0) / len;
		final float y112 = y11 - width * (x1 - x0) / len;

		final float x1c = x11 - width * .7f * (y1 - y0) / len;
		final float y1c = y11 + width * .5f * (x1 - x0) / len;
		final float x2c = x11 + width * .7f * (y1 - y0) / len;
		final float y2c = y11 - width * .5f * (x1 - x0) / len;

		path.reset();

		path.moveTo(x0, y0);
		path.lineTo(x1, y1);

		path.moveTo(x1, y1);
		path.quadTo(x1c, y1c, x111, y111);
		path.moveTo(x1, y1);
		path.quadTo(x2c, y2c, x112, y112);
	}

	public static String getShownTabsTitles(final Component parentComponent, final String delimiter) {
		final List<String> titles = getShownTabsTitles(parentComponent, new ArrayList<String>());
		final StringBuffer result = new StringBuffer(128);
		if (titles.size() > 0) {
			result.append(titles.get(0));
			for (int i = 1; i < titles.size(); i++) {
				result.append(delimiter).append(titles.get(i));
			}
		}
		return result.toString();
	}

	private static List<String> getShownTabsTitles(final Component parentComponent, List<String> tabTitles) {
		if (parentComponent instanceof Container) {
			final Container container = (Container) parentComponent;
			if (container instanceof JTabbedPane) {
				final JTabbedPane tabbedPane = (JTabbedPane) container;
				final int tabIndex = tabbedPane.getSelectedIndex();
				// check if tabIndex >= 0 in case that tab is not selected (due
				// to loading)
				if (tabbedPane.isShowing() && tabIndex >= 0) {
					tabTitles.add(tabbedPane.getTitleAt(tabIndex));
					tabTitles = getShownTabsTitles(tabbedPane.getComponentAt(tabIndex), tabTitles);
				}
			} else {
				for (final Component component : container.getComponents()) {
					tabTitles = getShownTabsTitles(component, tabTitles);
				}
			}
		}
		return tabTitles;
	}
}
