import { Song } from "./PlaylistComponent";

function PlaylistSongComponent({ title, artist, thumbnail }: Song) {
  return (
    <div className="w-full border bg-[#141414] p-3 rounded-lg mb-3">
      <div className="flex flex-row items-center">
        <img className="mr-6 w-14" src={thumbnail} alt="Album Cover" />
        <div className="flex flex-col">
          <div className="text-lg text-primary">{title}</div>
          <div className="text-sm text-white">{artist}</div>
        </div>
      </div>
    </div>
  );
}

export default PlaylistSongComponent;
