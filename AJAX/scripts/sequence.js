export class Sequence {
    constructor(id) {
      this.id = id;
      this._currentNummber = 0;
    }
    get next() {
      return ++this._currentNummber;
    }

    get currentNummber() {
      return this._currentNummber;
    }
  }