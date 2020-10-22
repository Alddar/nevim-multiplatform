import {Component, Input, OnInit} from '@angular/core'
import {faTimes} from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html'
})
export class ModalComponent implements OnInit {
  @Input()
  isOpen: boolean

  @Input()
  title: string

  faTimes = faTimes

  constructor() { }

  ngOnInit(): void {
  }

  open(): void {
    this.isOpen = true
  }
  close(): void {
    this.isOpen = false
  }
}
