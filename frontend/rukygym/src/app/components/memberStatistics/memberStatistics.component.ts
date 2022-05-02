import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { EChartsOption } from "echarts";

//import { StatisticsService } from "src/app/services/statistics.service";


@Component({
    selector:'memberstatistics',
    templateUrl: './memberStatistics.component.html',
    styleUrls: ['./memberStatistics.component.css']
})
export class MemberStatistics{

  option = {
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
        data: []
      },
      {
        name: '2020',
        type: 'bar',
        emphasis: {
          focus: 'series'
        },
        data: []
      },
      {
        name: '2021',
        type: 'bar',
        emphasis: {
          focus: 'series'
        },
        data: []
      },
      {
        name: '2022',
        type: 'bar',
        emphasis: {
          focus: 'series'
        },
        data: []
      },
    ]
  };

    _echartOption: EChartsOption;

    constructor(private router: Router, activatedRoute: ActivatedRoute, ){
      //public service: StatisticsService

        /*service.getAdminStats().subscribe(
          (result : any)=> {
            for(let i = 0; i < 4; i++){
              this.option.series[i].data = result[i];
            }*/
            this._echartOption = this.option as EChartsOption;
        /*  },
          error => alert("No se ha podido cargar los datos en estos momentos.")
        )*/

    }

    ngOnInit(): void {
    }
}
