    De naam en definitie van het principe.

    Single Responsibility Principle,
    * een klasse moet maar één verantwoordelijkheid hebben
  
    het doel is om makkelijk te onderhouden code te schrijven

    De consequenties van het toepassen van het principe.
    * kleinere klassen
    * modulaire klassen
    * makkelijk testen  

    * veel klassen 
    * veel boilerplate code
    * mogelijke overengineering

    een slecht voorbeeld van SRP:

        class Report {
            generateReport(data)
            mailReport(report)
            printReport(report)
        }

        dit is niet de bedoeling

    beter:

        class Report {
            generateReport(data)
        }

        class ReportMailer {
            mailReport(report)
        }

        class ReportPrinter {
            printReport(report)
        }

        public main() {
            report = generateReport()
            mailReport(report)
            printReport(report)
        }

        stel je wil nog meerdere andere dingen emailen, zou je bijvoorbeeld een klasse EmailService die wordt geextend bij je verschillende email verstuurders

    Op welk design property of properties het principe gebaseerd is.