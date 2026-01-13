
export function parseAvailableTime(raw) {
  if (!raw) return [];

  if (Array.isArray(raw)) {
    return raw.map(String).filter(t => t.trim());
  }

  const str = String(raw).trim();
  if (!str) return [];

  try {
    const parsed = JSON.parse(str);
    if (Array.isArray(parsed)) {
      return parsed.map(String).filter(t => t.trim());
    } else {
      return [str];
    }
  } catch (e) {
    return [str];
  }
}