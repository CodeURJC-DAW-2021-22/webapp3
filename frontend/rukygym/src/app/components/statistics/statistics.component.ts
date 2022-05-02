import { Component } from "@angular/core";
import { EChartsOption } from "echarts";


const option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    legend: {},
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        data: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
      }
    ],
    yAxis: [
      {
        type: 'value'
      }
    ],
    series: [
      {
        name: '2019',
        type: 'bar',
        emphasis: {
          focus: 'series'
        },
        data: [0,0,1,4,0,0,0,0,0,0,0,0]
      },
      {
        name: '2020',
        type: 'bar',
        emphasis: {
          focus: 'series'
        },
        data: [0,0,0,0,0,0,1,0,0,0,0,0]
      },
      {
        name: '2021',
        type: 'bar',
        emphasis: {
          focus: 'series'
        },
        data: [0,1,0,0,0,0,0,0,0,0,0,0]
      },
      {
        name: '2022',
        type: 'bar',
        emphasis: {
          focus: 'series'
        },
        data: [0,0,0,2,0,0,0,0,0,0,0,0]
      },
    ]
  };



@Component({
    selector:'statistics',
    templateUrl: './statistics.component.html',
    styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent {

    _echartOption: EChartsOption ;

    constructor(){
        this._echartOption = option as EChartsOption;
    }

    ngOnInit(): void {
    }
}
