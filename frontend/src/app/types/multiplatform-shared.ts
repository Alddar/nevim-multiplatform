type Nullable<T> = T | null | undefined

export class Player {
  constructor(name: string, id: string) {
  }

  readonly name: string
  readonly id: string
}

export class Lobby {
  constructor(public name: string,
              public id: string,
              public players: Array<Player>) {
  }
}

export class NameDTO {
  constructor(public name: string) {
  }
}

export class IdDTO {
  constructor(public id: string) {
  }
}

export class LobbyDTO {
  constructor(public lobby: Lobby) {
  }
}

export class CreateLobbyDTO {
  constructor(public name: string,
              public maxPlayers: number) {
  }
}

export class LobbiesDTO {
  constructor(public lobbies: Array<Lobby>) {
  }
}
