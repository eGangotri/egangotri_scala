object MixinTest extends App {


  val list = List(1,2,3,4,5,6,7,8,9)
  println(list.foldLeft(0)( (x,y)=> x+y))

  val sender1 = Sender("a@b.com", "C")
  val sender2 = sender1.copy(email="def@ghi.com")

  println(sender1)
  println(sender2)

  val someSms = SMS("12345", "Are you there?")
  val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")

  def showNotification(notification: Notification): String = {
    notification match {
      case Email(email, title, _) =>
        s"You got an email from $email with title: $title"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"
      case VoiceRecording(name, link) =>
        s"you received a Voice Recording from $name! Click the link to hear it: $link"
    }
  }

  println(showNotification(someSms))  // prints You got an SMS from 12345! Message: Are you there?

  println(showNotification(someVoiceRecording))

}


case class Sender(email:String, name:String)


abstract class Notification

case class Email(sender: String, title: String, body: String) extends Notification

case class SMS(caller: String, message: String) extends Notification

case class VoiceRecording(contactName: String, link: String) extends Notification
