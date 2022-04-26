import { Component, ElementRef, OnInit } from "@angular/core";
import { ChartConfiguration, Chart} from "chart.js";


const labelsLine = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December'
];

const dataLine = {
    labels: labelsLine,
    datasets: [{
        label: 'Año 2019',
        backgroundColor: 'rgb(255, 125, 98)',
        borderColor: 'rgb(255, 82, 0)',
        data: [0, 0, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0]
    }, {
        label: 'Año 2020',
        backgroundColor: 'rgb(136, 219, 255)',
        borderColor: 'rgb(0, 178, 255)',
        data: [0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0]
    }, {
        label: 'Año 2021',
        backgroundColor: 'rgb(126, 255, 146)',
        borderColor: 'rgb(59, 222, 84)',
        data: [0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    }, {
        label: 'Año 2022',
        backgroundColor: 'rgb(11, 2, 16)',
        borderColor: 'rgb(33, 2, 150)',
        data: [0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0]
    }, ]
};

const configLine : ChartConfiguration = {
    type: 'line',
    data: dataLine,
    options: {}
};

@Component({
    selector:'adminstatistics',
    templateUrl: './adminStatistics.component.html',
    styleUrls: ['./adminStatistics.component.css']
})
export class AdminStatistics implements OnInit{

    myChart:any;
   constructor(private elementRef: ElementRef) {
   }

  ngOnInit(){
   this.chartit();
  }

  chartit(){
     let htmlRef = this.elementRef.nativeElement.querySelector(`#chart`);
     this.myChart = new Chart(htmlRef, configLine);
  }

}