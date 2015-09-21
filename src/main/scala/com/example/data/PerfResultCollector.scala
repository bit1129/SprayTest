package com.example.data

import java.io._


import java.util.{Arrays, List, ArrayList}

object PerfResultCollector {
  def getData() = {

  }

  private def readFromFile(file: File, iteration: Int) = {
    var br: BufferedReader = null
    val lines = new ArrayList[String]
    try {
      br = new BufferedReader(new FileReader(file))
      var line: String = null
      val i: Int = 0
      while (i < iteration && (({
        line = br.readLine; line
      })) != null) {
        lines.add(line)
      }
      lines
    } finally {
      if (br != null) {
        br.close
      }
    }
  }


}
