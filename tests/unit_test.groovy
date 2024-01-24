import groovy.test.GroovyTestCase

class WordCountTest extends GroovyTestCase {
    String scriptFile = 'word_count.groovy'
    GroovyShell shell = new GroovyShell()
    def script = shell.parse(new File(scriptFile))

    // Test with an empty file
    void testEmptyFile() {
        def fileName = 'input_empty.txt'
        def wordCount = script.main("tests/${fileName}")

        assert wordCount == null
    }

    // Test with no argument
    void testNoArg() {
        String msg = shouldFail {
            script.main()
        }
        assert msg == 'Please provide a path to a file as an argument'
    }

    // Test with a non-existant file
    void testNoFile() {
        String msg = shouldFail {
            script.main('nothing')
        }
        assert msg == 'File does not exist'
    }

    // Test that using the application on the command line sorts as expected
    void testScript() {
        File expected_output_file = new File('tests/output.txt')
        String expected_output = expected_output_file.text

        StringBuilder stdout = new StringBuilder(), stderr = new StringBuilder()
        def proc = "groovy ${scriptFile} tests/input.txt".execute()
        proc.consumeProcessOutput(stdout, stderr)

        proc.waitForOrKill(5000)

        assert stdout.toString() == expected_output
    }

    // Test just the counting function works as expected
    void testCountWordsFunc() {
        def testFiles = ['input.txt', 'input_2.txt']

        testFiles.each { fileName ->
            def file = new File("tests/${fileName}")
            def wordCount = script.countWords(file)

            assert wordCount['et'] == 6
            assert wordCount['in'] == 4
            assert wordCount['eu'] == 4
            assert wordCount['id'] == 4
            assert wordCount['at'] == 4
            assert wordCount['ut'] == 3
            assert wordCount['sodales'] == 3
            assert wordCount['neque'] == 3
            assert wordCount['arcu'] == 3
            assert wordCount['tortor'] == 3
            assert wordCount['eget'] == 3
            assert wordCount['donec'] == 3
            assert wordCount['nunc'] == 3
            assert wordCount['vitae'] == 3
            assert wordCount['urna'] == 3
            assert wordCount['pellentesque'] == 3
            assert wordCount['nec'] == 3
            assert wordCount['ipsum'] == 2
            assert wordCount['dolor'] == 2
            assert wordCount['amet'] == 2
            assert wordCount['adipiscing'] == 2
            assert wordCount['sed'] == 2
            assert wordCount['tempor'] == 2
            assert wordCount['magna'] == 2
            assert wordCount['ac'] == 2
            assert wordCount['mi'] == 2
            assert wordCount['ultrices'] == 2
            assert wordCount['eros'] == 2
            assert wordCount['metus'] == 2
            assert wordCount['vulputate'] == 2
            assert wordCount['scelerisque'] == 2
            assert wordCount['felis'] == 2
            assert wordCount['venenatis'] == 2
            assert wordCount['tellus'] == 2
            assert wordCount['nisl'] == 2
            assert wordCount['morbi'] == 2
            assert wordCount['Eget'] == 2
            assert wordCount['tempus'] == 2
            assert wordCount['quam'] == 2
            assert wordCount['non'] == 2
            assert wordCount['risus'] == 2
            assert wordCount['quis'] == 2
            assert wordCount['tincidunt'] == 2
            assert wordCount['viverra'] == 2
            assert wordCount['semper'] == 2
            assert wordCount['Lorem'] == 1
            assert wordCount['sit'] == 1
            assert wordCount['consectetur'] == 1
            assert wordCount['elit'] == 1
            assert wordCount['do'] == 1
            assert wordCount['eiusmod'] == 1
            assert wordCount['incididunt'] == 1
            assert wordCount['labore'] == 1
            assert wordCount['dolore'] == 1
            assert wordCount['aliqua'] == 1
            assert wordCount['Non'] == 1
            assert wordCount['Gravida'] == 1
            assert wordCount['dignissim'] == 1
            assert wordCount['convallis'] == 1
            assert wordCount['aenean'] == 1
            assert wordCount['Ultricies'] == 1
            assert wordCount['mauris'] == 1
            assert wordCount['pharetra'] == 1
            assert wordCount['Vel'] == 1
            assert wordCount['odio'] == 1
            assert wordCount['orci'] == 1
            assert wordCount['Tellus'] == 1
            assert wordCount['Volutpat'] == 1
            assert wordCount['diam'] == 1
            assert wordCount['Tempor'] == 1
            assert wordCount['faucibus'] == 1
            assert wordCount['Sit'] == 1
            assert wordCount['luctus'] == 1
            assert wordCount['lectus'] == 1
            assert wordCount['fringilla'] == 1
            assert wordCount['Habitant'] == 1
            assert wordCount['tristique'] == 1
            assert wordCount['senectus'] == 1
            assert wordCount['netus'] == 1
            assert wordCount['lobortis'] == 1
            assert wordCount['Neque'] == 1
            assert wordCount['Pellentesque'] == 1
            assert wordCount['nam'] == 1
            assert wordCount['aliquam'] == 1
            assert wordCount['sem'] == 1
            assert wordCount['consequat'] == 1
            assert wordCount['porta'] == 1
            assert wordCount['Lacinia'] == 1
            assert wordCount['vel'] == 1
            assert wordCount['Velit'] == 1
            assert wordCount['laoreet'] == 1
            assert wordCount['Feugiat'] == 1
            assert wordCount['fermentum'] == 1
            assert wordCount['posuere'] == 1
            assert wordCount['Tortor'] == 1
            assert wordCount['In'] == 1
            assert wordCount['mollis'] == 1
            assert wordCount['Semper'] == 1
            assert wordCount['duis'] == 1
            assert wordCount['condimentum'] == 1
            assert wordCount['mattis'] == 1
            assert wordCount['Egestas'] == 1
            assert wordCount['purus'] == 1
            assert wordCount['accumsan'] == 1
            assert wordCount['nisi'] == 1
            assert wordCount['A'] == 1
            assert wordCount['cras'] == 1
            assert wordCount['auctor'] == 1
        }
    }
}

