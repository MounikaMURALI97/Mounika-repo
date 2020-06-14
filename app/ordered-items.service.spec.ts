import { TestBed } from '@angular/core/testing';

import { OrderedItemsService } from './ordered-items.service';

describe('OrderedItemsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OrderedItemsService = TestBed.get(OrderedItemsService);
    expect(service).toBeTruthy();
  });
});
