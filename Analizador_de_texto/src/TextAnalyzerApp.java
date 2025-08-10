import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextAnalyzerApp extends JFrame {

    // Componentes de la interfaz gráfica
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton processButton;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu;
    private JMenuItem openMenuItem, saveMenuItem, saveAsMenuItem, exitMenuItem;
    private JMenuItem copyMenuItem, cutMenuItem, pasteMenuItem, findMenuItem, replaceMenuItem;
    private JLabel lengthLabel, wordsLabel, firstLetterLabel, lastLetterLabel, centralLetterLabel;
    private JLabel firstWordLabel, centralWordLabel, lastWordLabel;
    private JLabel aRepetitionLabel, eRepetitionLabel, iRepetitionLabel, oRepetitionLabel, uRepetitionLabel;
    private JLabel parWordsLabel, imparWordsLabel;
    private File currentFile;

    // Constantes para la traducción
    private static final String KEY_MURCIELAGO = "MURCIELAGO";
    private static final String KEY_NUMBERS = "0123456789";

    public TextAnalyzerApp() {
        // Configuración de la ventana principal
        setTitle("PROGRAMACION II - MANEJO DE CADENAS");
        // Establecer la ventana para que se abra maximizada
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Inicializar componentes
        initComponents();
        
        // Crear el layout
        setupLayout();
        
        // Configurar el menú
        setupMenu();
        
        // Configurar los listeners
        setupListeners();
    }

    private void initComponents() {
        // Áreas de texto
        inputTextArea = new JTextArea();
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true); // Para que el texto se ajuste y no se salga de la ventana
        outputTextArea.setWrapStyleWord(true); // Para que el salto de línea sea por palabra

        // Botón
        processButton = new JButton("Procesar");

        // Menú
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Archivo");
        editMenu = new JMenu("Editar");

        // Ítems del menú Archivo
        openMenuItem = new JMenuItem("Abrir");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke("control A"));
        saveMenuItem = new JMenuItem("Guardar");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke("control G"));
        saveAsMenuItem = new JMenuItem("Guardar como");
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke("F12"));
        exitMenuItem = new JMenuItem("Salir");

        // Ítems del menú Editar
        copyMenuItem = new JMenuItem("Copiar");
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke("control C"));
        cutMenuItem = new JMenuItem("Cortar");
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke("control X"));
        pasteMenuItem = new JMenuItem("Pegar");
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke("control V")); // El documento original tenía Ctrl+P, pero V es el estándar
        findMenuItem = new JMenuItem("Buscar");
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke("control B"));
        replaceMenuItem = new JMenuItem("Reemplazar");
        replaceMenuItem.setAccelerator(KeyStroke.getKeyStroke("control R"));

        // Etiquetas para el análisis de texto
        lengthLabel = new JLabel("Longitud del texto.......:");
        wordsLabel = new JLabel("Total de palabras.......:");
        firstLetterLabel = new JLabel("Primer letra del texto.:");
        lastLetterLabel = new JLabel("Última letra del texto.:");
        centralLetterLabel = new JLabel("Letra central del texto:");
        firstWordLabel = new JLabel("Primera palabra.........:");
        centralWordLabel = new JLabel("Palabra central.............:");
        lastWordLabel = new JLabel("Última palabra.............:");
        
        aRepetitionLabel = new JLabel("Repeticiones de 'A', 'a' ó 'á':");
        eRepetitionLabel = new JLabel("Repeticiones de 'E', 'e' ó 'é':");
        iRepetitionLabel = new JLabel("Repeticiones de 'I', 'i' ó 'í':");
        oRepetitionLabel = new JLabel("Repeticiones de 'O', 'o' ó 'ó':");
        uRepetitionLabel = new JLabel("Repeticiones de 'U', 'u' ó 'ú':");
        parWordsLabel = new JLabel("Palabras con cantidad de caracteres par:");
        imparWordsLabel = new JLabel("Palabras con cantidad de caracteres impar:");
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior para la entrada de texto y el botón
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Ingrese un texto o abra un archivo"));
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.setPreferredSize(new Dimension(0, 150)); // Altura inicial para el área de entrada
        topPanel.add(inputScrollPane, BorderLayout.CENTER);
        topPanel.add(processButton, BorderLayout.SOUTH);
        
        // Panel central para los resultados del análisis
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(8, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Análisis de Texto"));
        centerPanel.add(lengthLabel);
        centerPanel.add(aRepetitionLabel);
        centerPanel.add(wordsLabel);
        centerPanel.add(eRepetitionLabel);
        centerPanel.add(firstLetterLabel);
        centerPanel.add(iRepetitionLabel);
        centerPanel.add(lastLetterLabel);
        centerPanel.add(oRepetitionLabel);
        centerPanel.add(centralLetterLabel);
        centerPanel.add(uRepetitionLabel);
        centerPanel.add(firstWordLabel);
        centerPanel.add(parWordsLabel);
        centerPanel.add(centralWordLabel);
        centerPanel.add(imparWordsLabel);
        centerPanel.add(lastWordLabel);
        
        // Panel inferior para la traducción
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("TRADUCCIÓN A CLAVE MURCIELAGO"));
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setPreferredSize(new Dimension(0, 200)); // Altura inicial para el área de salida
        bottomPanel.add(outputScrollPane, BorderLayout.CENTER);
        
        // Agregar paneles a la ventana principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void setupMenu() {
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        editMenu.add(copyMenuItem);
        editMenu.add(cutMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.addSeparator();
        editMenu.add(findMenuItem);
        editMenu.add(replaceMenuItem);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        setJMenuBar(menuBar);
    }

    private void setupListeners() {
        // Listener del botón Procesar
        processButton.addActionListener(this::processText);

        // Listener para la tecla Enter en el área de texto
        inputTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // No se necesita implementación para este evento
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Si la tecla presionada es Enter, procesar el texto
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    processText(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No se necesita implementación para este evento
            }
        });

        // Listeners del menú Archivo
        openMenuItem.addActionListener(e -> openFile());
        saveMenuItem.addActionListener(e -> saveFile());
        saveAsMenuItem.addActionListener(e -> saveAsFile());
        exitMenuItem.addActionListener(e -> System.exit(0));

        // Listeners del menú Editar
        copyMenuItem.addActionListener(e -> inputTextArea.copy());
        cutMenuItem.addActionListener(e -> inputTextArea.cut());
        pasteMenuItem.addActionListener(e -> inputTextArea.paste());
        findMenuItem.addActionListener(e -> findText());
        replaceMenuItem.addActionListener(e -> replaceText());
    }

    private void processText(ActionEvent e) {
        String text = inputTextArea.getText();
        
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un texto para procesar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 1. Longitud del texto
        lengthLabel.setText("Longitud del texto.......: " + text.length());

        // 2. Total de palabras
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        wordsLabel.setText("Total de palabras.......: " + wordCount);

        // 3. Primera y última letra
        if (text.length() > 0) {
            firstLetterLabel.setText("Primer letra del texto.: " + text.charAt(0));
            lastLetterLabel.setText("Última letra del texto.: " + text.charAt(text.length() - 1));
        } else {
            firstLetterLabel.setText("Primer letra del texto.: ");
            lastLetterLabel.setText("Última letra del texto.: ");
        }

        // 4. Letra central
        if (text.length() > 0) {
            int centerIndex = text.length() / 2;
            if (text.length() % 2 != 0) { // Impar
                centralLetterLabel.setText("Letra central del texto: " + text.charAt(centerIndex));
            } else { // Par
                centralLetterLabel.setText("Letra central del texto: " + text.charAt(centerIndex - 1) + text.charAt(centerIndex));
            }
        } else {
            centralLetterLabel.setText("Letra central del texto: ");
        }
        
        // 5. Primera, última y central palabra
        if (wordCount > 0) {
            firstWordLabel.setText("Primera palabra.........: " + words[0]);
            lastWordLabel.setText("Última palabra.............: " + words[wordCount - 1]);
            centralWordLabel.setText("Palabra central.............: " + words[wordCount / 2]);
        } else {
            firstWordLabel.setText("Primera palabra.........: ");
            lastWordLabel.setText("Última palabra.............: ");
            centralWordLabel.setText("Palabra central.............: ");
        }
        
        // 6. Repeticiones de vocales
        aRepetitionLabel.setText("Repeticiones de 'A', 'a' ó 'á': " + countOccurrences(text, "AaÁá"));
        eRepetitionLabel.setText("Repeticiones de 'E', 'e' ó 'é': " + countOccurrences(text, "EeÉé"));
        iRepetitionLabel.setText("Repeticiones de 'I', 'i' ó 'í': " + countOccurrences(text, "IiÍí"));
        oRepetitionLabel.setText("Repeticiones de 'O', 'o' ó 'ó': " + countOccurrences(text, "OoÓó"));
        uRepetitionLabel.setText("Repeticiones de 'U', 'u' ó 'ú': " + countOccurrences(text, "UuÚú"));
        
        // 7. Palabras con cantidad de caracteres par e impar
        int parCount = 0;
        int imparCount = 0;
        for (String word : words) {
            if (word.length() % 2 == 0) {
                parCount++;
            } else {
                imparCount++;
            }
        }
        parWordsLabel.setText("Palabras con cantidad de caracteres par: " + parCount);
        imparWordsLabel.setText("Palabras con cantidad de caracteres impar: " + imparCount);

        // 8. Traducción a clave Murciélago
        outputTextArea.setText(translateToMurcielago(text));
    }
    
    private int countOccurrences(String text, String charsToCount) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (charsToCount.contains(String.valueOf(c))) {
                count++;
            }
        }
        return count;
    }

    private String translateToMurcielago(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            char upperChar = Character.toUpperCase(c);
            int index = KEY_MURCIELAGO.indexOf(upperChar);
            if (index != -1) {
                result.append(KEY_NUMBERS.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Texto (.txt)", "txt"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                inputTextArea.setText(""); // Limpiar el área de texto
                String line;
                while ((line = reader.readLine()) != null) {
                    inputTextArea.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        if (currentFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                writer.write(inputTextArea.getText());
                JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            saveAsFile();
        }
    }
    
    private void saveAsFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Texto (.txt)", "txt"));
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            // Asegurarse de que el archivo tenga la extensión .txt
            if (!currentFile.getName().toLowerCase().endsWith(".txt")) {
                currentFile = new File(currentFile.getAbsolutePath() + ".txt");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                writer.write(inputTextArea.getText());
                JOptionPane.showMessageDialog(this, "Archivo guardado como " + currentFile.getName(), "Guardar Como", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void findText() {
        String textToFind = JOptionPane.showInputDialog(this, "Ingrese el texto a buscar:", "Buscar", JOptionPane.PLAIN_MESSAGE);
        if (textToFind != null && !textToFind.isEmpty()) {
            String fullText = inputTextArea.getText();
            int index = fullText.indexOf(textToFind);
            if (index != -1) {
                inputTextArea.setSelectionStart(index);
                inputTextArea.setSelectionEnd(index + textToFind.length());
                inputTextArea.requestFocusInWindow();
            } else {
                JOptionPane.showMessageDialog(this, "Texto no encontrado.", "Buscar", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void replaceText() {
        String textToFind = JOptionPane.showInputDialog(this, "Ingrese el texto a reemplazar:", "Reemplazar", JOptionPane.PLAIN_MESSAGE);
        if (textToFind != null && !textToFind.isEmpty()) {
            String textToReplace = JOptionPane.showInputDialog(this, "Ingrese el nuevo texto:", "Reemplazar", JOptionPane.PLAIN_MESSAGE);
            if (textToReplace != null) {
                String fullText = inputTextArea.getText();
                String newText = fullText.replace(textToFind, textToReplace);
                inputTextArea.setText(newText);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextAnalyzerApp().setVisible(true));
    }
}

