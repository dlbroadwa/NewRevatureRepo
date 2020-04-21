package com.ex.io;

public class BarcodeReader {
    private final InputSource input;

    public BarcodeReader(InputSource in) {
        input = in;
    }

    /** Reads a barcode from the input source.
     * Returns -1 if the input is blank, or 0 if the input is not a well-formed barcode.
     *
     * @return A positive integer barcode on success, -1 if the input is blank,
     * or 0 if the input is not a valid barcode.
     */
    public int readBarcode() {
        int barcode = 0;
        String in = input.getInput();
        if (in == null || in.isEmpty())
            return -1;
        try {
            barcode = Integer.parseInt(in);
            if (barcode <= 0)
                barcode = 0;
        }
        catch (NumberFormatException ex) {
            barcode = 0;
        }

        return barcode;
    }
}
