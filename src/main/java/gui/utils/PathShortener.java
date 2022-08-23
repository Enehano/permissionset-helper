package gui.utils;

//todo test on Windows/Mac/Linux
public class PathShortener {
    public static final int DEFAULT_SHORTENER_THRESHOLD = 4;
    public static final String SHORTENER_BACKSLASH_REGEX = "/";
    public static final String SHORTENER_SLASH_REGEX = "/";
    public static final String SHORTENER_BACKSLASH = "/";
    public static final String SHORTENER_SLASH = "/";
    public static final String SHORTENER_ELLIPSE = "...";

    public static String shortenPath(final String path) {
        return shortenPath(path, DEFAULT_SHORTENER_THRESHOLD);
    }

    /**
     * Shortens the path based on the given maximum number of path elements. E.g.,
     * "C:/1/2/test.txt" returns "C:/1/.../test.txt" if threshold is 1.
     *
     * @param path the path to the file (relative or absolute)
     * @param threshold the number of directories to keep unshortened
     * @return shortened path
     */
    public static String shortenPath(final String path, final int threshold) {
        String regex = SHORTENER_BACKSLASH_REGEX;
        String sep = SHORTENER_BACKSLASH;

        if (path.indexOf("/") > 0) {
            regex = SHORTENER_SLASH_REGEX;
            sep = SHORTENER_SLASH;
        }

        String pathtemp[] = path.split(regex);
        // remove empty elements
        int elem = 0;
        {
            final String newtemp[] = new String[pathtemp.length];
            int j = 0;
            for (int i = 0; i < pathtemp.length; i++) {
                if (!pathtemp[i].equals("")) {
                    newtemp[j++] = pathtemp[i];
                    elem++;
                }
            }
            pathtemp = newtemp;
        }

        if (elem > threshold) {
            final StringBuilder sb = new StringBuilder();
            int index = 0;

            // drive or protocol
            final int pos2dots = path.indexOf(":");
            if (pos2dots > 0) {
                // case c:\ c:/ etc.
                sb.append(path.substring(0, pos2dots + 2));
                index++;
                // case http:// ftp:// etc.
                if (path.indexOf(":/") > 0 && pathtemp[0].length() > 2) {
                    sb.append(SHORTENER_SLASH);
                }
            } else {
                final boolean isUNC = path.startsWith(SHORTENER_BACKSLASH_REGEX);
                if (isUNC) {
                    sb.append(SHORTENER_BACKSLASH);
                }
            }

            for (; index <= threshold; index++) {
                sb.append(pathtemp[index]).append(sep);
            }

            if (index == (elem - 1)) {
                sb.append(pathtemp[elem - 1]);
            } else {
                sb.append(SHORTENER_ELLIPSE).append(sep).append(pathtemp[elem - 1]);
            }
            return sb.toString();
        }
        return path;
    }
}
