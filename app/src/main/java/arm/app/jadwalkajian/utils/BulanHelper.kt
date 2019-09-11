package arm.app.jadwalkajian.utils

class BulanHelper{

    fun convertToBulan(bulan: String): String? {

        var namaBulan:String? = null
        when (bulan) {
            "1" -> {
                namaBulan = "Januari"
            }
            "2" -> {
                namaBulan = "Februari"
            }
            "3" -> {
                namaBulan = "Maret"
            }
            "4" -> {
                namaBulan = "April"
            }
            "5" -> {
                namaBulan = "Mei"
            }
            "6" -> {
                namaBulan = "Juni"
            }
            "7" -> {
                namaBulan = "Juli"
            }
            "8" -> {
                namaBulan = "Agustus"
            }
            "9" -> {
                namaBulan = "September"
            }
            "10" -> {
                namaBulan = "Oktober"
            }
            "11" -> {
                namaBulan = "November"
            }
            "12" -> {
                namaBulan = "Desember"
            }
            "01" -> {
                namaBulan = "Januari"
            }
            "02" -> {
                namaBulan = "Februari"
            }
            "03" -> {
                namaBulan = "Maret"
            }
            "04" -> {
                namaBulan = "April"
            }
            "05" -> {
                namaBulan = "Mei"
            }
            "06" -> {
                namaBulan = "Juni"
            }
            "07" -> {
                namaBulan = "Juli"
            }
            "08" -> {
                namaBulan = "Agustus"
            }
            "09" -> {
                namaBulan = "September"
            }
        }

        return namaBulan

    }

}