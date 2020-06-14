import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdereddetailsComponent } from './ordereddetails.component';

describe('OrdereddetailsComponent', () => {
  let component: OrdereddetailsComponent;
  let fixture: ComponentFixture<OrdereddetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdereddetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdereddetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
