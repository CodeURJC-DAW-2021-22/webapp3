import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { EChartsOption } from "echarts";
import { LoginService } from "src/app/services/login.service";
import { StatisticsService } from "src/app/services/statistics.service";

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
        data: [''] ,
      }
    ],
    yAxis: [
      {
        type: 'value'
      }
    ],
    series: [
      {
        name: '2022',
        type: 'bar',
        emphasis: {
          focus: 'series'
        },
        data: [0]
      },
    ]
  };

    _echartOption!: EChartsOption;

    constructor(private router: Router, activatedRoute: ActivatedRoute, service: StatisticsService, login: LoginService){

      service.getUserStats().subscribe(
            (result: any)=> {
              //console.log(result[0])
              for(let i = 0; i < 20; i++){
                this.option.xAxis[0].data.push('Tabla ' + i);
              }
              for (let i = 0; i < 20; i++){
                this.option.series[0].data.push(result[i]);
              }
              this._echartOption = this.option as EChartsOption;
            },
            error => alert("No se ha podido cargar los datos en estos momentos.")
          )}
    

    ngOnInit(): void {
    }
}
