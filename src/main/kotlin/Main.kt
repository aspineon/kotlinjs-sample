import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    println("init")

    getBitcoinPrice()
}

fun getBitcoinPrice() {
    console.log("getBitcoinPrice()")

    val selectConversionCode = document.getElementById("selectConversionCode") as HTMLSelectElement
    selectConversionCode.addEventListener("change", {
        console.log("change to ${selectConversionCode.value}")
    })

    val btnGetBtcPrice = document.getElementById("btnGetBtcPrice") as HTMLButtonElement
    btnGetBtcPrice.addEventListener("click", {
        val xhr: dynamic = XMLHttpRequest()

        xhr.open("GET", "https://api.coindesk.com/v1/bpi/currentprice.json", true)
        xhr.onreadystatechange = fun() {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status == 200) {
                val jsonResult: dynamic = JSON.parse(xhr.responseText)
                val code = selectConversionCode.value;

                console.log("getBitcoinPrice result ${xhr.status}", jsonResult)
                window.alert(jsonResult.bpi[code].rate + " " + jsonResult.bpi[code].code)
            }
        }
        xhr.send()
    })
}
