package learning

object ExPattern extends App{

  val col = EmailAddress("QA","a@bcd.com")
  val col2 = PhoneBook("QA","999999")
  val col3 = 10
  matcher(col2)
  def matcher(col:Notification) = {
    col match {
      case n:EmailAddress  => println (s"Em Add: $n")
      case p:PhoneBook => println (s"Phone $p")
    }
  }
}
abstract class Notification
class EmailAddress(val name:String, val email: String) extends Notification
object EmailAddress {

  def apply(nom:String, em:String) = {
    new EmailAddress(nom,em)
  }

  def unapply(emailAdd:EmailAddress) ={
    Some(emailAdd.name + emailAdd.email)
  }
}

class PhoneBook(val name:String, val phoneNum: String) extends Notification

object PhoneBook {

  def apply(nom:String, ph:String) = {
    new EmailAddress(nom,ph)
  }

  def unapply(phoneBook: PhoneBook) ={
    Some(phoneBook.name, phoneBook.phoneNum)
  }
}
