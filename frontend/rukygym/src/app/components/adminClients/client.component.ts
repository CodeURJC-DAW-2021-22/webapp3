import { Component, ViewChild } from '@angular/core'
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/User.model';
import { UserService } from 'src/app/services/User.service';


@Component({
    selector: "clienttable",
    templateUrl: "./client.component.html",
    styleUrls: ["./client.component.css",]
})
export class ClientTable {
    page = 0;
    clients: User [] | undefined;
    show : boolean = true;
    ids: User [] = [];

    @ViewChild('moreClients')
    but: HTMLButtonElement | undefined;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService) {
        service.getMembers(0).subscribe(
            clients => this.clients = clients as User [],
            error => alert("No fue posible cargar los clientes del servidor. Inténtelo más tarde.")
        )
    }

    more() {
        this.page++;
        this.service.getMembers(this.page).subscribe(
            clients => {
                for (let e of clients as User [])
                        this.clients?.push(e);
                if ((clients as User []).length != 3) {
                    this.show = false;
                }
            },
            error => alert("No fue posible cargar los clientes del servidor. Inténtelo más tarde.")
        )
    }

    select(member: User | undefined) {
        if (this.ids.includes(member as User))
            this.ids = this.ids.filter(e => e != member);
        else
            this.ids.push(member as User);
    }

    delete(){
        for (let e of this.ids){
            this.service.deleteMember(e).subscribe(
                _ => window.location.reload(),
                error => alert("No pudo eliminarse el cliente: " + e.name),
            );
        }
    }
}