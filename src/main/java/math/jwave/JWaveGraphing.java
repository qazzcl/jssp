/**
 * JWaveGraphing is distributed under the MIT License (MIT); this file is part of.
 *
 * Copyright (c) 2015 Christian Scheiblich (cscheiblich@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package math.jwave;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * JWaveGraphing - a GUI for using JWave library on images.
 *
 * @author Christian Scheiblich (cscheiblich@gmail.com)
 * @date 16.05.2015 19:56:19
 */
// git clone https://github.com/cscheiblich/JWaveGraphing.git
public class JWaveGraphing extends JFrame {

    private final String _version = "0.13"; // version of JWaveGraphing
    private final String _date = "16.05.2015"; // date of release
    private final String _author = "Christian Scheiblich"; // author name
    private final String _email = "cscheiblich@gmail.com"; // author email

    /**
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 22:22:10
     */
    private static final long serialVersionUID = 2038965267122479551L;

    /**
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 22:22:26
     */
    private JPanel _contentPane;
    private JScrollPane _scrollPane = new JScrollPane( );

    /**
     * JWave objects - Transform, BasicTransform, and Wavelet.
     *
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 23:00:37
     */
    protected String _selectedTransform = "Fast Wavelet Transform";
    protected String _selectedWavelet = "Haar";

    /**
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 22:47:08
     */
    private JMenuBar _menuBar = new JMenuBar( );

    /**
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 22:47:45
     */
    private JMenu _menuMain = new JMenu( "main" );
    private JMenuItem _menuItemLoad = new JMenuItem( "load" );
    private JMenuItem _menuItemExit = new JMenuItem( "exit" );

    /**
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 22:49:03
     */
    private JMenu _menuAlgorithm = new JMenu( "algorithm" );
    private JMenuItem _menuItemFWT = new JMenuItem( "Fast Wavelet Transform" );
    private JMenuItem _menuItemWPT = new JMenuItem( "Wavelet Packet Transform" );
    private JMenuItem _menuItemDFT = new JMenuItem( "Discrete Fourier Transform" );

    /**
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 22:50:55
     */
    private JMenu _menuWavelet = new JMenu( "wavelet" );
    private JMenuItem _menuItemHaar = new JMenuItem( "Haar" );

    private JMenu _menuDaubechies = new JMenu( "Daubechies" );
    private JMenuItem _menuItemDaub2 = new JMenuItem( "Daubechies 2" );
    private JMenuItem _menuItemDaub3 = new JMenuItem( "Daubechies 3" );
    private JMenuItem _menuItemDaub4 = new JMenuItem( "Daubechies 4" );
    private JMenuItem _menuItemDaub5 = new JMenuItem( "Daubechies 5" );
    private JMenuItem _menuItemDaub20 = new JMenuItem( "Daubechies 20" );

    private JMenu _menuCoiflet = new JMenu( "Coiflet" );
    private JMenuItem _menuItemCoif1 = new JMenuItem( "Coiflet 1" );
    private JMenuItem _menuItemCoif2 = new JMenuItem( "Coiflet 2" );
    private JMenuItem _menuItemCoif3 = new JMenuItem( "Coiflet 3" );

    private final JMenu _menuSymlets = new JMenu( "Symlets" );
    private final JMenuItem _menuItemSym2 = new JMenuItem( "Symlet 2" );
    private final JMenuItem _menuItemSym20 = new JMenuItem( "Symlet 20" );
    /**
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 22:51:44
     */
    private final JMenu _menuAbout = new JMenu( "about" );
    private final JMenuItem _menuItemVersion = new JMenuItem( "version: "
            + _version );
    private final JMenuItem _menuItemDate = new JMenuItem( "date: " + _date );
    private final JMenuItem _menuItemAuthor = new JMenuItem( _author );
    private final JMenuItem _menuItemEmail = new JMenuItem( _email );

    /**
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 23:38:06
     */
    private final JButton _buttonForward = new JButton( "forward" );
    private final JButton _buttonReverse = new JButton( "reverse" );

    /**
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 23:49:19
     */
    protected BufferedImage _bufferedImage = null;

    /**
     * Launch the application.
     *
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 22:23:29
     * @param args
     */
    public static void main( String[ ] args ) {

        EventQueue.invokeLater( new Runnable( ) {

            public void run( ) {

                try {
                    JWaveGraphing frame = new JWaveGraphing( );
                    frame.setVisible( true );
                } catch( Exception e ) {
                    e.printStackTrace( );
                } // try

            } // Runnable

        } ); // EventQueue

    } // main

    /**
     * Create the frame.
     */
    public JWaveGraphing( ) {

        setTitle( "JWaveGraphing" );

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        setBounds( 100, 100, 450, 300 );

        setJMenuBar( _menuBar );

        // main
        _menuBar.add( _menuMain );
        _menuItemExit.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent arg0 ) {
                System.exit( 0 ); // raw drop
            }
        } );
        _menuItemLoad.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {

                JFileChooser jFileChooser = new JFileChooser( );
                int returnVal = jFileChooser.showOpenDialog( _contentPane );
                if( returnVal == JFileChooser.APPROVE_OPTION ) {

                    File file = jFileChooser.getSelectedFile( );

                    _bufferedImage = loadBufferedImage( file );

                    _contentPane.removeAll( );
                    paintBufferedImage( _contentPane, _bufferedImage );
                    _contentPane.updateUI( );

                } // if
            }
        } );

        _menuMain.add( _menuItemLoad );
        _menuMain.add( _menuItemExit );

        // algorithm
        _menuBar.add( _menuAlgorithm );
        _menuItemDFT.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedTransform = "Discrete Fourier Transform";
            }
        } );

        _menuAlgorithm.add( _menuItemDFT );
        _menuItemFWT.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedTransform = "Fast Wavelet Transform";
            }
        } );
        _menuAlgorithm.add( _menuItemFWT );
        _menuItemWPT.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedTransform = "Wavelet Packet Transform";
            }
        } );
        _menuAlgorithm.add( _menuItemWPT );

        // Haar Wavelet
        _menuBar.add( _menuWavelet );
        _menuItemHaar.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Haar";
            }
        } );
        _menuWavelet.add( _menuItemHaar );

        // Daubechies Wavelets
        _menuWavelet.add( _menuDaubechies );
        _menuItemDaub2.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Daubechies 2";
            }
        } );
        _menuDaubechies.add( _menuItemDaub2 );
        _menuItemDaub3.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Daubechies 3";
            }
        } );
        _menuDaubechies.add( _menuItemDaub3 );
        _menuItemDaub4.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Daubechies 4";
            }
        } );
        _menuDaubechies.add( _menuItemDaub4 );
        _menuItemDaub5.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Daubechies 5";
            }
        } );
        _menuDaubechies.add( _menuItemDaub5 );
        _menuItemDaub20.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Daubechies 20";
            }
        } );
        _menuDaubechies.add( _menuItemDaub20 );

        // Coiflet Wavelets
        _menuWavelet.add( _menuCoiflet );
        _menuItemCoif1.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Coiflet 1";
            }
        } );
        _menuCoiflet.add( _menuItemCoif1 );
        _menuItemCoif2.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Coiflet 2";
            }
        } );
        _menuCoiflet.add( _menuItemCoif2 );
        _menuItemCoif3.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Coiflet 3";
            }
        } );
        _menuCoiflet.add( _menuItemCoif3 );

        // Symlets Wavelets
        _menuWavelet.add( _menuSymlets );
        _menuItemSym2.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Symlet 2";
            }
        } );
        _menuSymlets.add( _menuItemSym2 );
        _menuItemSym20.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {
                _selectedWavelet = "Symlet 20";
            }
        } );
        _menuSymlets.add( _menuItemSym20 );

        // help
        _menuBar.add( _menuAbout );
        _menuItemVersion.setEnabled( false );
        _menuAbout.add( _menuItemVersion );
        _menuItemDate.setEnabled( false );
        _menuAbout.add( _menuItemDate );
        _menuItemAuthor.setEnabled( false );
        _menuAbout.add( _menuItemAuthor );
        _menuItemEmail.setEnabled( false );
        _menuAbout.add( _menuItemEmail );

        _buttonForward.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {

                BufferedImage transformedBufferedImage =
                        transformForwardByARGBintArr( _bufferedImage );

                if( transformedBufferedImage == null )
                    return;

                _contentPane.removeAll( );
                paintBufferedImage( _contentPane, transformedBufferedImage );
                _contentPane.updateUI( );

                _bufferedImage = transformedBufferedImage;

            }
        } );

        _menuBar.add( _buttonForward );

        _buttonReverse.addActionListener( new ActionListener( ) {
            public void actionPerformed( ActionEvent e ) {

                BufferedImage transformedBufferedImage =
                        transformReverseByARGBintArr( _bufferedImage );

                if( transformedBufferedImage == null )
                    return;

                _contentPane.removeAll( );
                paintBufferedImage( _contentPane, transformedBufferedImage );
                _contentPane.updateUI( );

                _bufferedImage = transformedBufferedImage;

            }
        } );
        _menuBar.add( _buttonReverse );

        _contentPane = new JPanel( );
        _contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        setContentPane( _contentPane );
        _contentPane.setLayout( new BorderLayout( 0, 0 ) );
        _contentPane.add( _scrollPane, BorderLayout.CENTER );

    } // JWaveGraphing

    /**
     * loading from File to a buffered image
     *
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 15.05.2015 23:47:37
     * @param file
     * @return
     */
    protected BufferedImage loadBufferedImage( File file ) {

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read( file );
        } catch( IOException exception ) {
            exception.printStackTrace( );
        } // try

        return bufferedImage;

    } // loadBufferedImage

    protected void paintBufferedImage( JPanel contentPane,
                                       BufferedImage bufferedImage ) {

        contentPane.add( new JLabel( new ImageIcon( bufferedImage ) ) );

    } // paintBufferedImage

    /**
     * Transform a BufferedImage of size 2^p x 2^q | p,q � N using a selected
     * transform and a selected wavelet. However, the BufferedIamge is convert to
     * integer values of 24 bit range. This results in rounding errors, that
     * appears as a broken transform, but it is not!
     *
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 17.05.2015 10:02:36
     * @param bufferedImage
     * @return
     */
    protected BufferedImage transformForwardByARGBintArr(
            BufferedImage bufferedImage ) {

        if( bufferedImage == null )
            return null;

        // quick an dirty
        Transform t =
                TransformBuilder.create( _selectedTransform, _selectedWavelet );

        int width = bufferedImage.getWidth( );
        int height = bufferedImage.getHeight( );

        //        int[ ] arrDeCompWidth = null;
        //        int[ ] arrDeCompHeight = null;
        //
        //        try {
        //          arrDeCompWidth = MathToolKit.decompose( width );
        //          arrDeCompHeight = MathToolKit.decompose( height );
        //        } catch( JWaveException e1 ) {
        //          e1.printStackTrace( );
        //        }

        int[ ][ ] matImageRGBintger =
                convertBufferdImage2matixIntegerCodedARGB( bufferedImage );

        double[ ][ ] matImagRGBdouble = castIntMat2DblMat( matImageRGBintger );

        double[ ][ ] matImageRGBdoubleForward = t.forward( matImagRGBdouble );

        int[ ][ ] matImageRGBintegerForward =
                castDblMat2IntMat( matImageRGBdoubleForward );

        // TODO some bug due to getRGB of BufferedImage ..
        BufferedImage transformedBufferedImage =
                new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );
        for( int i = 0; i < matImageRGBintegerForward.length; i++ ) {
            for( int j = 0; j < matImageRGBintegerForward[ 0 ].length; j++ ) {
                int pixel = matImageRGBintegerForward[ j ][ i ];
                transformedBufferedImage.setRGB( i, j, pixel );
            }
        }

        return transformedBufferedImage;

    } // transformForwardByARGBintArr

    /**
     * Transform a BufferedImage of size 2^p x 2^q | p,q � N using a selected
     * transform and a selected wavelet. However, the BufferedIamge is convert to
     * integer values of 24 bit range. This results in rounding errors, that
     * appears as a broken transform, but it is not! *
     *
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 17.05.2015 10:04:16
     * @param bufferedImage
     * @return
     */
    protected BufferedImage transformReverseByARGBintArr(
            BufferedImage bufferedImage ) {

        if( bufferedImage == null )
            return null;

        Transform t =
                TransformBuilder.create( _selectedTransform, _selectedWavelet );

        int width = bufferedImage.getWidth( );
        int height = bufferedImage.getHeight( );

        int[ ][ ] matImageRGBintger =
                convertBufferdImage2matixIntegerCodedARGB( bufferedImage );

        double[ ][ ] matImagRGBdouble = castIntMat2DblMat( matImageRGBintger );

        double[ ][ ] matImageRGBdoubleForward = t.reverse( matImagRGBdouble );

        int[ ][ ] matImageRGBintegerForward =
                castDblMat2IntMat( matImageRGBdoubleForward );

        // TODO some bug due to getRGB of BufferedImage ..
        BufferedImage transformedBufferedImage =
                new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );
        for( int i = 0; i < matImageRGBintegerForward.length; i++ ) {
            for( int j = 0; j < matImageRGBintegerForward[ 0 ].length; j++ ) {
                int pixel = matImageRGBintegerForward[ j ][ i ];
                transformedBufferedImage.setRGB( i, j, pixel );
            }
        }

        return transformedBufferedImage;

    } // transformReverseByARGBintArr

    /**
     * convert image to ARGB
     *
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 16.05.2015 00:23:09
     * @param image
     * @return
     */
    private int[ ][ ] convertBufferdImage2matixIntegerCodedARGB(
            BufferedImage image ) {

        int width = image.getWidth( );
        int height = image.getHeight( );

        // Color color = new Color( image.getRGB(  x, y ) );
        // Color[][] colors = new Color[ width ][ height ];

        int[ ][ ] matIntCodedRGB = new int[ width ][ height ];

        //    22222222 111111
        //    bitpos    32109876 54321098 76543210
        //    ------------------------------------
        //    bits      RRRRRRRR GGGGGGGG BBBBBBBB

        // Components will be in the range of 0..255:
        //    int blue = color & 0xff;
        //    int green = ( color & 0xff00 ) >> 8;
        //    int red = ( color & 0xff0000 ) >> 16;

        //    Your color is color = -16755216 which has:
        //
        //      blue : 240         // Strong blue
        //      green:  85         // A little green mixed in
        //      red  :   0         // No red component at all
        //      alpha: 255         // Completely opaque

        for( int row = 0; row < height; row++ ) {
            for( int col = 0; col < width; col++ ) {
                matIntCodedRGB[ col ][ row ] = image.getRGB( col, row );
            }
        }

        return matIntCodedRGB;

    } // convertBufferdImage2matixIntegerCodedARGB

    /**
     * cast integer matrix to double matrix
     *
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 16.05.2015 00:24:24
     * @param mat
     * @return
     */
    private double[ ][ ] castIntMat2DblMat( int[ ][ ] mat ) {

        double[ ][ ] dblMat = new double[ mat.length ][ mat[ 0 ].length ];

        for( int i = 0; i < mat.length; i++ )
            for( int j = 0; j < mat[ 0 ].length; j++ )
                dblMat[ i ][ j ] = (double)mat[ i ][ j ];

        return dblMat;

    } // castIntMat2DblMat

    /**
     * cast double matrix to integer matrix
     *
     * @author Christian Scheiblich (cscheiblich@gmail.com)
     * @date 16.05.2015 00:24:40
     * @param mat
     * @return
     */
    private int[ ][ ] castDblMat2IntMat( double[ ][ ] mat ) {

        int[ ][ ] intMat = new int[ mat.length ][ mat[ 0 ].length ];

        for( int i = 0; i < mat.length; i++ )
            for( int j = 0; j < mat[ 0 ].length; j++ )
                intMat[ i ][ j ] = (int)mat[ i ][ j ];

        return intMat;

    } // castDblMat2IntMat

} // JWaveGraphing
